package jpamock.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.GeneratedValue;


public class RecursiveSearch {

	private PropertyCallBack propertyCallBack;
	private Reflection reflection = Reflection.get();
	private List<String> avoidCyclicField = new ArrayList<String>();

	public RecursiveSearch(PropertyCallBack propertyCallBack) {
		this.propertyCallBack = propertyCallBack;
	}

	public void start(Object rootEntity) {
		avoidCyclicField.clear();
		startReal(rootEntity, null);
	}

	private void startReal(Object rootEntity, Integer index) {
		if (rootEntity != null) {
			PropertyDescription propertyDescription = new PropertyDescription();
			propertyDescription.setParent(rootEntity);
			if (reflection.isBeanType(rootEntity.getClass()) && !Collection.class.isAssignableFrom(rootEntity.getClass())) {
				for (Field field : reflection.getAllDeclaredFields(rootEntity.getClass())) {
					propertyDescription = new PropertyDescription();
					propertyDescription.setParent(rootEntity);
					if (Modifier.isStatic(field.getModifiers()))
						continue;
					String temp = "";
					if (index != null)
						temp = index.toString();
					if (!avoidCyclicField.contains(rootEntity.getClass() + field.toString() + temp)) {
						avoidCyclicField.add(rootEntity.getClass() + field.toString() + temp);
						field.setAccessible(true);
						propertyDescription.setField(field);
						if (reflection.isAnnotationPresent(field, GeneratedValue.class))
							propertyDescription.setIsAutoIncrementId(true);
						if (index != null)
							propertyDescription.setIndex(index);
						propertyCallBack.propertyCallBack(propertyDescription);
						startReal(reflection.getProperty(rootEntity, field), null);
					}

				}
			} else {
				if (index != null)
					propertyDescription.setIndex(index);
				propertyCallBack.propertyCallBack(propertyDescription);
				if (Collection.class.isAssignableFrom(rootEntity.getClass())) {
					Collection<?> collection = (Collection<?>) rootEntity;
					int count = 0;
					if (collection != null) {
						for (Object object : collection) {
							startReal(object, count);
							count++;
						}
					}

				}
			}
		}
	}
}
