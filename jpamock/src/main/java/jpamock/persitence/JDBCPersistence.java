package jpamock.persitence;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import jpamock.reflection.RecursiveSearch;
import jpamock.reflection.Reflection;

public class JDBCPersistence {

	private EntityManagerFactory entityManagerFactory;
	private PrepareIds prepareIds;
	private Reflection reflection = Reflection.get();
	private DBHelper dbHelper;

	public JDBCPersistence(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public Object persist(Object rootEntity, Map<String, Object> specialFields) {
		if (entityManagerFactory != null) {
			Connection connection = null;
			EntityManager entityManager = null;
			try {
				entityManager = entityManagerFactory.createEntityManager();
				entityManager.getTransaction().begin();
				connection = getConnection(entityManager);
				dbHelper = new DBHelper(connection);
				dbHelper.disableConstaints();
				prepareIds = new PrepareIds(new DataHelper());
				prepareIds.prepare(rootEntity, entityManager, specialFields);
				return persistReal(rootEntity, entityManager, connection);
			} catch (Throwable t) {
				if (entityManager.getTransaction().isActive())
					entityManager.getTransaction().rollback();
				throw new RuntimeException(t);
			} finally {
				if (entityManager.getTransaction().isActive()) {
					dbHelper.enableConstaints();
					entityManager.getTransaction().commit();
				}
				entityManager.close();
			}
		}
		return rootEntity;
	}

	public boolean containsById(List<?> list, Object mock) {
		Object idMock = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(mock);
		for (Object object : list) {
			Object idElement = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(object);
			if (idElement.equals(idMock))
				return true;
		}
		return false;
	}

	public boolean containsAllById(List<?> list1, List<?> list2) {
		for (Object object1 : list1) {
			Object id1 = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(object1);
			for (Object object2 : list2) {
				Object id2 = entityManagerFactory.getPersistenceUnitUtil().getIdentifier(object2);
				if (!id1.equals(id2))
					return false;
			}
		}
		return true;
	}

	public void clearAll() {
		new DBHelper().clearAll(entityManagerFactory);
	}

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

	/* PRIVATE */

	private Object persistReal(Object rootEntity, EntityManager entityManager, Connection connection) throws Exception {
		final LinkedList<Entry<Object, InsertDeletStatement>> allToPersist = fillAllToPersist(rootEntity);
		dbHelper.prepareStatments(allToPersist, connection);
		while (!isEveryBodyClosed(allToPersist)) {
			Entry<Object, InsertDeletStatement> entryTemp = allToPersist.remove(0);
			Object entity = entryTemp.getKey();
			PreparedStatement ps = entryTemp.getValue().getInsert();
			allToPersist.add(entryTemp); // send to end of queue
			if (ps.isClosed())
				continue;
			Savepoint savePoint = connection.setSavepoint(String.valueOf(System.nanoTime()));
			try {
				ps.executeUpdate();
				Field fieldId = dbHelper.getId(entity);
				if (fieldId != null) {
					Object id = reflection.getProperty(entity, fieldId);
					if (id != null) {
						notifySubClasses(id, entity, allToPersist);
					} else {
						ResultSet rs = ps.getGeneratedKeys();
						if (rs.next()) {
							if (fieldId.getType() == Long.class) {
								id = rs.getLong(1);
								reflection.setProperty(entity, fieldId, id);
							} else if (fieldId.getType() == Integer.class) {
								id = rs.getInt(1);
								reflection.setProperty(entity, fieldId, id);
							} else {
								throw new RuntimeException("Implement type " + fieldId.getType() + " on " + this.getClass().getName());
							}
							notifySubClasses(id, entity, allToPersist);
							if (allToPersist.size() > 1) {
								dbHelper.prepareStatments(allToPersist, connection);
								dbHelper.deleteAll(allToPersist, connection);
							}
						}
					}
				}
				ps.close();
			} catch (Throwable t) {
				connection.rollback(savePoint);
			}
		}// end while

		for (Entry<Object, InsertDeletStatement> entry : allToPersist)
			if (!entry.getValue().getDelete().isClosed())
				entry.getValue().getDelete().close();

		return rootEntity;
	}

	private LinkedList<Entry<Object, InsertDeletStatement>> fillAllToPersist(Object rootEntity) {
		final LinkedList<Entry<Object, InsertDeletStatement>> allToPersist = new LinkedList<Entry<Object, InsertDeletStatement>>();
		new RecursiveSearch(propertyDescription -> {
			
			Object object = propertyDescription.getParent();
			if (object.getClass().isAnnotationPresent(Entity.class)) {
				for (Entry<Object, InsertDeletStatement> entry : allToPersist)
					if (entry.getKey().equals(object))
						return;
				allToPersist.add(new SimpleEntry<Object, InsertDeletStatement>(propertyDescription.getParent(), null));
			}
				
		}).start(rootEntity);
		return allToPersist;
	}

	private void notifySubClasses(Object id, Object entity, LinkedList<Entry<Object, InsertDeletStatement>> allToPersist) {
		for (Entry<Object, InsertDeletStatement> entry : allToPersist) {
			if (entry.getKey().getClass().getSuperclass() != Object.class) {
				if (entry.getKey().getClass().getSuperclass() == entity.getClass()) {
					Field fieldId = dbHelper.getId(entry.getKey());
					reflection.setProperty(entry.getKey(), fieldId, id);
				}
			}
		}
	}

	private boolean isEveryBodyClosed(LinkedList<Entry<Object, InsertDeletStatement>> allToPersist) {
		try {
			for (Entry<Object, InsertDeletStatement> entry : allToPersist) {
				PreparedStatement ps = entry.getValue().getInsert();
				if (ps == null || !ps.isClosed())
					return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
