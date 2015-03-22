package jpamock.persitence;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import jpamock.reflection.RecursiveSearch;
import jpamock.reflection.Reflection;

public class PrepareIds {

	private Reflection reflection = Reflection.get();
	private DataHelper dataHelper;

	/**
	 * Receives DataHelper via injection.
	 * @param dataHelper
	 */
	public PrepareIds(DataHelper dataHelper) {
		this.dataHelper = dataHelper;
	}
	
	public void prepare(Object rootEntity, final EntityManager entityManager, final Map<String, Object> specialFields) {
		new RecursiveSearch(propertyDescription -> {
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
									
									Object object = dataHelper.getData(entityManager, propertyDescription, field);
									reflection.setProperty(propertyDescription.getParent(), field, object);
									
									break;
								}
							}
						} else {
							if (reflection.isAnnotationPresent(propertyDescription.getField(), Id.class)) {
								Object object = dataHelper.getData(entityManager, propertyDescription, propertyDescription.getField());
								reflection.setProperty(propertyDescription.getParent(), propertyDescription.getField(), object);
							}
						}

					}
					Column colum = (Column) Reflection.get().getAnnotation(propertyDescription.getField(),
							Column.class);
					if (colum != null && colum.unique()
							&& !reflection.isAnnotationPresent(propertyDescription.getField(), GeneratedValue.class)) {
						Object object = dataHelper.getData(entityManager, propertyDescription, propertyDescription.getField());
						reflection.setProperty(propertyDescription.getParent(), propertyDescription.getField(), object);
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
									
									Object object = dataHelper.getData(entityManager, propertyDescription, tempField);
									reflection.setProperty(propertyDescription.getParent(), propertyDescription.getField(), object);
									
								}
							}
						}
					}

				}
			
		}).start(rootEntity);
	}
}
