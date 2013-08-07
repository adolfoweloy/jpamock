package jpamock.persitence;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import jpamock.reflection.PropertyCallBack;
import jpamock.reflection.PropertyDescription;
import jpamock.reflection.RecursiveSearch;
import jpamock.reflection.Reflection;

public class PrepareIds {

	private Reflection reflection = Reflection.get();
	private SecureRandom random = new SecureRandom();

	public void prepare(Object rootEntity, final EntityManager entityManager, final Map<String, Object> specialFields) {
		new RecursiveSearch(new PropertyCallBack() {
			public void propertyCallBack(PropertyDescription propertyDescription) {
				if (propertyDescription.getParent().getClass().isAnnotationPresent(Entity.class)) {
					if (propertyDescription.getIsAutoIncrementId()) {
						String temp = "";
						if (propertyDescription.getIndex() != null)
							temp = propertyDescription.getIndex().toString();
						if (!specialFields.containsKey(propertyDescription.getField().toString() + temp))
							reflection.setProperty(propertyDescription.getParent(), propertyDescription.getField(),
									null);
					} else {
						if (propertyDescription.getField() == null) {
							Object id = entityManager.getEntityManagerFactory().getPersistenceUnitUtil()
									.getIdentifier(propertyDescription.getParent());
							for (Field field : propertyDescription.getParent().getClass().getDeclaredFields()) {
								Object idTemp = reflection.getProperty(propertyDescription.getParent(), field);
								if (id == idTemp && reflection.isAnnotationPresent(field, Id.class)
										&& !reflection.isAnnotationPresent(field, GeneratedValue.class)) {
									setData(entityManager, propertyDescription, field);
									break;
								}
							}
						} else {
							if (reflection.isAnnotationPresent(propertyDescription.getField(), Id.class)) {
								setData(entityManager, propertyDescription, propertyDescription.getField());
							}
						}

					}
					Column colum = (Column) Reflection.get().getAnnotation(propertyDescription.getField(),
							Column.class);
					if (colum != null && colum.unique()
							&& !reflection.isAnnotationPresent(propertyDescription.getField(), GeneratedValue.class)) {
						setData(entityManager, propertyDescription, propertyDescription.getField());
					}

					Table table = propertyDescription.getParent().getClass().getAnnotation(Table.class);
					if (table != null) {
						UniqueConstraint[] ucs = table.uniqueConstraints();
						if (ucs != null) {
							for (UniqueConstraint uniqueConstraint : ucs) {
								for (String columnName : uniqueConstraint.columnNames()) {
									Field tempField = null;
									try {
										tempField = reflection.getField(propertyDescription.getParent().getClass(),
												columnName);
									} catch (Exception e) {
										for (Field field : propertyDescription.getParent().getClass()
												.getDeclaredFields()) {
											Column column = (Column) reflection.getAnnotation(field,
													Column.class);
											if (column != null && columnName.equals(column.name())) {
												tempField = field;
												break;
											}
										}
									}
									setData(entityManager, propertyDescription, tempField);
								}
							}
						}
					}

				}
			}

			private void setData(final EntityManager entityManager, PropertyDescription propertyDescription, Field field) {
				String str = "select max(" + field.getName() + ") from "
						+ propertyDescription.getParent().getClass().getName();
				Object object = entityManager.createQuery(str).getSingleResult();
				if (object != null) {
					if (object instanceof Byte) {
						Byte temp = (Byte) object;
						object = ++temp;
					} else if (object instanceof Short) {
						Short temp = (Short) object;
						object = ++temp;
					} else if (object instanceof Integer) {
						Integer temp = (Integer) object;
						object = ++temp;
					} else if (object instanceof Long) {
						Long temp = (Long) object;
						object = ++temp;
					} else if (object instanceof Float) {
						Float temp = (Float) object;
						object = ++temp;
					} else if (object instanceof Double) {
						Double temp = (Double) object;
						object = ++temp;
					} else if (object instanceof String) {
						object = null;
						while (object == null) {
							object = new BigInteger(32, random).toString(32);
							String hql = "select " + field.getName() + " from "
									+ propertyDescription.getParent().getClass().getName() + " where "
									+ field.getName() + " = :temp";
							Query q = entityManager.createQuery(hql).setParameter("temp", object);
							try {
								object = q.getSingleResult();
							} catch (NoResultException nre) {

							}
						}
					} else {
						throw new RuntimeException("Type not predicted (" + object.getClass()
								+ "). Change PrepareIds.java!");
					}
					reflection.setProperty(propertyDescription.getParent(), field, object);
				}
			}
		}).start(rootEntity);
	}
}
