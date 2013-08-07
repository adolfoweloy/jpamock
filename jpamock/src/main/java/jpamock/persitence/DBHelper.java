package jpamock.persitence;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import jpamock.instance.SmartClass;
import jpamock.reflection.Reflection;

public class DBHelper {

	Connection connection;
	List<String> constraints;
	Reflection reflection = Reflection.get();

	public DBHelper(Connection connection) {
		this.connection = connection;
	}

	public DBHelper() {

	}

	public void clearAll(EntityManagerFactory entityManagerFactory) {
		List<String> queries = new ArrayList<String>();
		EntityManager entityManager = null;
		String lastException = "";
		Savepoint savePoint = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			// TODO enable others providers
			connection = getConnection(entityManager);

			List<String> tables = getTables();
			for (String table : tables)
				queries.add("delete from " + table);
			int count = 0;
			// TODO disble triggers too
			disableConstaints();
			while (queries.size() > 0) {
				savePoint = connection.setSavepoint(String.valueOf(System.nanoTime()));
				if (count == 500)
					throw new RuntimeException("Seems infinity loop! Contact project people..." + lastException);
				count++;
				String query = queries.remove(0);
				Statement statement = null;
				try {
					statement = connection.createStatement();
					statement.executeUpdate(query);
				} catch (Throwable t) {
					if (savePoint != null)
						connection.rollback(savePoint);
					lastException = t.getMessage();
					queries.add(query);
				} finally {
					statement.close();
				}
			}
			// TODO enable triggers too
			enableConstaints();
			entityManager.getTransaction().commit();
		} catch (Throwable t) {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			throw new RuntimeException(t);
		} finally {
			entityManager.close();
		}
	}

	/* TODO duplicado */
	private Connection getConnection(EntityManager entityManager) {
		// TODO enable others providers
		try {
			Connection connection;
			Object session = entityManager.unwrap(Class.forName("org.hibernate.Session"));
			connection = (Connection) session.getClass().getDeclaredMethod("connection", new Class[0]).invoke(session);
			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getTables() throws SQLException {
		List<String> tables = new ArrayList<String>();
		ResultSet rs = connection.getMetaData().getTables(null, null, "%", new String[] { "TABLE" });
		while (rs.next())
			tables.add(rs.getString(3));
		return tables;
	}

	public void disableConstaints() {
		try {
			constraints = new ArrayList<String>();
			if (isMysql(connection)) {
				Statement stmt = connection.createStatement();
				stmt.execute("SET FOREIGN_KEY_CHECKS=0");
				stmt.close();
			} else if (isPostgres(connection)) {
				Statement stmt = connection.createStatement();
				StringBuilder fk = new StringBuilder();
				fk.append(" SELECT c.relname, conname, pg_get_constraintdef(r.oid, false) as condef");
				fk.append(" FROM  pg_constraint r, pg_class c");
				fk.append(" WHERE r.contype = 'f'");
				fk.append(" and r.conrelid=c.oid");
				ResultSet rs = stmt.executeQuery(fk.toString());
				while (rs.next()) {
					Statement stmtAlter = connection.createStatement();
					stmtAlter.execute("ALTER TABLE " + rs.getString(1) + " DROP CONSTRAINT " + rs.getString(2));
					stmtAlter.close();
					constraints.add(rs.getString(1) + "#" + rs.getString(2) + "#" + rs.getString(3));
				}
				stmt.close();
			} else if (isSQLServer(connection)) {
				Statement stmt = connection.createStatement();
				stmt.execute("ALTER TABLE ? NOCHECK CONSTRAINT all");
				stmt.close();
			} else if (isOracle(connection)) {
				StringBuilder procedure = new StringBuilder();
				procedure.append(" BEGIN");
				procedure.append("   FOR c IN");
				procedure.append("   (SELECT c.owner, c.table_name, c.constraint_name");
				procedure.append("    FROM user_constraints c, user_tables t");
				procedure.append("    WHERE c.table_name = t.table_name");
				procedure.append("    AND c.status = 'ENABLED'");
				procedure.append("    ORDER BY c.constraint_type DESC)");
				procedure.append("   LOOP");
				procedure.append("     dbms_utility.exec_ddl_statement('alter table ' || c.owner || '.' || c.table_name || ' disable constraint ' || c.constraint_name);");
				procedure.append("  END LOOP;");
				procedure.append(" END;");
				Statement stmt = connection.createStatement();
				stmt.executeUpdate(procedure.toString());
				stmt.close();
			} else {
				throw new RuntimeException("disableConstaints not implemented for " + connection.getMetaData().getDatabaseProductName());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void enableConstaints() {
		try {
			if (isMysql(connection)) {
				Statement stmt = connection.createStatement();
				stmt.execute("SET FOREIGN_KEY_CHECKS=1");
				stmt.close();
			} else if (isPostgres(connection) && constraints != null) {
				for (String constraint : constraints) {
					Statement stmt = connection.createStatement();
					String[] col = constraint.split("#");
					stmt.execute("ALTER TABLE " + col[0] + " ADD CONSTRAINT " + col[1] + " " + col[2]);
					stmt.close();
				}
			} else if (isSQLServer(connection)) {
				Statement stmt = connection.createStatement();
				stmt.execute("ALTER TABLE ? CHECK CONSTRAINT all");
				stmt.close();
			} else if (isOracle(connection)) {
				StringBuilder procedure = new StringBuilder();
				procedure.append(" BEGIN");
				procedure.append("   FOR c IN");
				procedure.append("   (SELECT c.owner, c.table_name, c.constraint_name");
				procedure.append("    FROM user_constraints c, user_tables t");
				procedure.append("    WHERE c.table_name = t.table_name");
				procedure.append("    AND c.status = 'DISABLED'");
				procedure.append("    ORDER BY c.constraint_type)");
				procedure.append("   LOOP");
				procedure.append("     dbms_utility.exec_ddl_statement('alter table ' || c.owner || '.' || c.table_name || ' enable constraint ' || c.constraint_name);");
				procedure.append("   END LOOP;");
				procedure.append(" END;");
				Statement stmt = connection.createStatement();
				stmt.executeUpdate(procedure.toString());
				stmt.close();
			} else {
				throw new RuntimeException("enableConstaints not implemented for " + connection.getMetaData().getDatabaseProductName());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isMysql(Connection connection) {
		try {
			return connection.getMetaData().getURL().contains(":mysql:");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isPostgres(Connection connection) {
		try {
			return connection.getMetaData().getURL().contains(":postgresql:");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isSQLServer(Connection connection) {
		try {
			return connection.getMetaData().getURL().contains(":sqlserver:");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isOracle(Connection connection) {
		try {
			return connection.getMetaData().getURL().contains(":oracle:");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void prepareStatments(LinkedList<Entry<Object, InsertDeletStatement>> allToPersist, Connection connection) throws SQLException {
		LinkedList<Entry<Object, InsertDeletStatement>> hidden = new LinkedList<Map.Entry<Object, InsertDeletStatement>>();
		for (Entry<Object, InsertDeletStatement> entry : allToPersist) {
			if (entry.getValue() != null) {
				entry.getValue().getInsert().close();
				entry.getValue().getDelete().close();
			}
			createStatement(connection, entry);
			List<Entry<Object, InsertDeletStatement>> entryTemp = createStatementForHiddenTables(connection, entry.getKey(), allToPersist);
			hidden.addAll(entryTemp);
		}

		for (Entry<Object, InsertDeletStatement> hid : hidden) {
			boolean add = true;
			for (Entry<Object, InsertDeletStatement> entryTemp : allToPersist) {
				if (entryTemp.getKey().equals(hid.getKey())) {
					entryTemp.setValue(hid.getValue());
					add = false;
					break;
				}
			}
			if (add)
				allToPersist.add(hid);
		}

	}

	public Field getId(Object object) {
		List<Field> fields;
		if (object instanceof Class<?>) {
			fields = reflection.getAllDeclaredFields((Class<?>) object);
		} else {
			fields = reflection.getAllDeclaredFields(object.getClass());
		}

		for (Field field : fields)
			if (reflection.isAnnotationPresent(field, Id.class))
				return field;
		return null;
	}

	public void deleteAll(LinkedList<Entry<Object, InsertDeletStatement>> deletes, Connection connection) {
		while (!isEveryBodyClosed(deletes)) {
			Entry<Object, InsertDeletStatement> entry = deletes.remove(0);
			PreparedStatement preparedStatement = entry.getValue().getDelete();
			try {
				if (!preparedStatement.isClosed()) {
					preparedStatement.executeUpdate();
					preparedStatement.close();
				}
			} catch (Exception e) {
				//try again
			} finally {
				deletes.add(entry);
			}
		}
	}

	/* PRIVATE */

	private boolean isEveryBodyClosed(LinkedList<Entry<Object, InsertDeletStatement>> deletes) {
		try {
			for (Entry<Object, InsertDeletStatement> entry : deletes) {
				PreparedStatement ps = entry.getValue().getDelete();
				if (ps == null || !ps.isClosed())
					return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void createStatement(Connection connection, Entry<Object, InsertDeletStatement> entry) {
		if (entry.getKey().getClass() == String.class)
			return;
		Map<String, Object> values = getMap(entry.getKey());
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO " + getTableName(entry.getKey().getClass()));
		sql.append(" (" + prepareColumn(values, entry.getKey(), connection) + ")");
		sql.append(" VALUES (" + prepareHolder(values, entry.getKey(), connection) + ")");
		try {
			InsertDeletStatement insertDeletStatement = new InsertDeletStatement();
			PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			insertDeletStatement.setInsert(ps);
			int count = 1;
			Field fieldId = getId(entry.getKey());
			String id = getColumnName(fieldId);
			for (Entry<String, Object> entryTemp : values.entrySet()) {
				Object temp = entryTemp.getValue();
				if (temp != null && temp.getClass().isEnum()) {
					Field ordinal = reflection.getField(temp.getClass(), "ordinal");
					ps.setObject(count, reflection.getProperty(temp, ordinal)); // TODO:
					// try
					// find
					// @Enumerated
				} else {

					if (entryTemp.getKey().equals(id) && entryTemp.getValue() == null && isPostgres(connection)) {
						SequenceGenerator sg = (SequenceGenerator) reflection.getAnnotation(fieldId, SequenceGenerator.class);
						if (sg != null && sg.sequenceName() != null) {
							continue;
						}
					}

					if (temp instanceof Date && isPostgres(connection)) {
						Date dt = (Date) temp;
						ps.setDate(count, new java.sql.Date(dt.getTime()));
					} else {
						ps.setObject(count, temp);
					}
				}
				count++;
			}
			/********** DELETE *********/

			String del = "delete from " + getTableName(entry.getKey().getClass()) + " where " + id + " = ?";
			PreparedStatement delete = connection.prepareStatement(del);
			delete.setObject(1, reflection.getProperty(entry.getKey(), fieldId));
			insertDeletStatement.setDelete(delete);

			entry.setValue(insertDeletStatement);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	private List<Entry<Object, InsertDeletStatement>> createStatementForHiddenTables(Connection connection, Object obj, LinkedList<Entry<Object, InsertDeletStatement>> allToPersist) {
		List<Entry<Object, InsertDeletStatement>> entryTemp = new LinkedList<Map.Entry<Object, InsertDeletStatement>>();
		List<Field> fields = reflection.getAllDeclaredFields(obj.getClass());
		for (Field field : fields) {
			OneToMany otm = (OneToMany) reflection.getAnnotation(field, OneToMany.class);
			ManyToMany mtm = (ManyToMany) reflection.getAnnotation(field, ManyToMany.class);
			if (otm != null && otm.mappedBy().equals("")) {
				Class<?> clazzTemp = obj.getClass();
				if (obj.getClass().getSuperclass() != Object.class) {
					Inheritance inheritance = obj.getClass().getSuperclass().getAnnotation(Inheritance.class);
					if (inheritance != null && inheritance.strategy() == InheritanceType.JOINED) {
						clazzTemp = obj.getClass().getSuperclass();
					}
				}
				String table1 = getTableName(clazzTemp);
				Class<?> beanType = new SmartClass(field).getBeanType();
				String table2 = getTableName(beanType);
				String table1Table2 = table1 + "_" + table2;
				Field fieldId = getId(obj);

				Field fieldIdDep = getId(new SmartClass(field).getBeanType());
				String sql = "INSERT INTO " + table1Table2 + " (" + table1 + "_" + getColumnName(getId(obj)) + "," + getColumnName(field) + "_" + getColumnName(fieldIdDep)
						+ ") VALUES (?,?)";
				try {
					Collection<?> coll = (Collection<?>) reflection.getProperty(obj, field);
					for (Object dep : coll) {
						PreparedStatement ps = connection.prepareStatement(sql.toString());
						Object id = reflection.getProperty(obj, fieldId);
						ps.setObject(1, id);
						Field fieldIdDep2 = getId(dep);
						Object idDep = reflection.getProperty(dep, fieldIdDep2);
						ps.setObject(2, idDep);

						String del = "delete from " + table1Table2 + " where " + table1 + "_" + getColumnName(getId(obj)) + " = ?";
						PreparedStatement delete = connection.prepareStatement(del);
						delete.setObject(1, id);

						InsertDeletStatement insertDeletStatement = new InsertDeletStatement();
						insertDeletStatement.setInsert(ps);
						insertDeletStatement.setDelete(delete);

						entryTemp.add(new SimpleEntry<Object, InsertDeletStatement>(table1Table2 + idDep, insertDeletStatement));
					}
				} catch (Throwable t) {
					throw new RuntimeException(t);
				}
			}
			if (mtm != null && mtm.mappedBy().equals("")) {

				String tableInter = null;
				String column1 = null;
				String column2 = null;

				JoinTable jt = (JoinTable) reflection.getAnnotation(field, JoinTable.class);
				if (jt != null && jt.name() != null) {
					tableInter = jt.name();
					//TODO que array estranho
					column1 = jt.joinColumns()[0].name();
					column2 = jt.inverseJoinColumns()[0].name();
				} else {
					Class<?> clazzTemp = obj.getClass();
					if (obj.getClass().getSuperclass() != Object.class) {
						Inheritance inheritance = obj.getClass().getSuperclass().getAnnotation(Inheritance.class);
						if (inheritance != null && inheritance.strategy() == InheritanceType.JOINED) {
							clazzTemp = obj.getClass().getSuperclass();
						}
					}
					String table1 = getTableName(clazzTemp);

					Class<?> beanType = new SmartClass(field).getBeanType();
					String table2 = getTableName(beanType);
					tableInter = table1 + "_" + table2;
					Field fieldIdDep = getId(new SmartClass(field).getBeanType());
					column1 = table1+ "_" + getColumnName(getId(obj));
					column2 = getColumnName(field) + "_" + getColumnName(fieldIdDep);

				}

				String sql = "INSERT INTO " + tableInter + " (" + column1 + "," + column2 + ") VALUES (?,?)";
				try {
					Collection<?> coll = (Collection<?>) reflection.getProperty(obj, field);
					for (Object dep : coll) {
						PreparedStatement ps = connection.prepareStatement(sql.toString());
						Field fieldId = getId(obj);
						Object id = reflection.getProperty(obj, fieldId);
						ps.setObject(1, id);
						Field fieldIdDep2 = getId(dep);
						Object idDep = reflection.getProperty(dep, fieldIdDep2);
						ps.setObject(2, idDep);

						String del = "delete from " + tableInter + " where " + column1 + " = ?";
						PreparedStatement delete = connection.prepareStatement(del);
						delete.setObject(1, id);

						InsertDeletStatement insertDeletStatement = new InsertDeletStatement();
						insertDeletStatement.setInsert(ps);
						insertDeletStatement.setDelete(delete);

						entryTemp.add(new SimpleEntry<Object, InsertDeletStatement>(tableInter + idDep, insertDeletStatement));
					}
				} catch (Throwable t) {
					throw new RuntimeException(t);
				}

			}
		}

		return entryTemp;
	}

	private String prepareColumn(Map<String, Object> values, Object obj, Connection connection) {
		String columns = "";
		for (Entry<String, Object> entry : values.entrySet())
			columns += "," + entry.getKey();
		return columns.substring(1);
	}

	private String getTableName(Class<?> clazz) {
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = clazz.getAnnotation(Table.class);
			if (table.name() != null)
				return table.name();
		}
		return clazz.getSimpleName();
	}

	private String prepareHolder(Map<String, Object> values, Object obj, Connection connection) {
		Field fieldId = getId(obj);
		String id = getColumnName(fieldId);
		String columns = "";
		for (Entry<String, Object> entry : values.entrySet()) {
			if (entry.getKey().equals(id) && entry.getValue() == null && isPostgres(connection)) {
				SequenceGenerator sg = (SequenceGenerator) reflection.getAnnotation(fieldId, SequenceGenerator.class);
				if (sg != null && sg.sequenceName() != null) {
					columns += ",nextval('" + sg.sequenceName() + "') ";
				}
			} else {
				columns += ",?";
			}
		}
		return columns.substring(1);
	}

	private Map<String, Object> getMap(Object object) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Field> fields = reflection.getAllDeclaredFields(object.getClass());
		for (Field field : fields) {
			if (object.getClass().getSuperclass() != Object.class) {
				Inheritance inheritance = object.getClass().getSuperclass().getAnnotation(Inheritance.class);
				if (inheritance != null && inheritance.strategy() == InheritanceType.JOINED) {
					if (!reflection.isAnnotationPresent(field, Id.class)) {
						try {
							object.getClass().getSuperclass().getDeclaredField(field.getName());
							continue;
						} catch (Exception e) {
							// not superclass field
						}
					}
				}
			}
			if (!reflection.isAnnotationPresent(field, Transient.class) && !Modifier.isStatic(field.getModifiers())) {
				String columnName = getColumnName(field);
				if (!Collection.class.isAssignableFrom(field.getType())) {
					Object obj = reflection.getProperty(object, field);
					if (obj != null && obj.getClass().isAnnotationPresent(Entity.class))
						obj = reflection.getProperty(obj, getId(obj));
					map.put(columnName, obj);
				}
			}
		}
		return map;
	}

	private String getColumnName(Field field) {
		String columnName = field.getName();
		if (reflection.isAnnotationPresent(field, Column.class)) {
			Column column = (Column) reflection.getAnnotation(field, Column.class);
			if (column.name() != null && !"".equals(column.name()))
				columnName = column.name();
		} else if (reflection.isAnnotationPresent(field, JoinColumn.class)) {
			JoinColumn joinColumn = (JoinColumn) reflection.getAnnotation(field, JoinColumn.class);
			if (joinColumn.name() != null && !"".equals(joinColumn.name()))
				columnName = joinColumn.name();
		} else if (reflection.isAnnotationPresent(field, ManyToOne.class) || reflection.isAnnotationPresent(field, OneToOne.class)) {
			columnName = columnName + "_id"; // hibernate auto ddl
		}
		// TODO Joincolumns, inverse join columns
		return columnName;
	}

}