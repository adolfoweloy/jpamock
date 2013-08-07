package jpamock.instance;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class FieldLookup {

	private NewInstance newInstance;
	private Map<Class<?>, Object> defaultMap;
	private List<Field> avoidCyclic = new ArrayList<Field>();

	public FieldLookup(NewInstance newInstance, Map<Class<?>, Object> defaultMap) {
		this.newInstance = newInstance;
		this.defaultMap = defaultMap;
	}

	public void lookup(SmartField smartField) {
		Field[] fields = smartField.getClazz().getDeclaredFields();
		for (Field fieldTemp : fields) {
			System.out.println(fieldTemp);
			if (!avoidCyclic.contains(fieldTemp)) {
				avoidCyclic.add(fieldTemp);
				Class<?> fieldClass = fieldTemp.getType();
				if (Modifier.isStatic(fieldTemp.getModifiers()))
					continue; // ignore
				SmartField smartFieldNew = new SmartField(smartField, fieldClass, fieldTemp.getName());
				//System.out.println(fieldClass);
				smartField.addChild(smartFieldNew);
				if (fieldClass.getClassLoader() == null && !Collection.class.isAssignableFrom(fieldClass))
					continue; // end of tree
				lookup(smartFieldNew);
				if (Collection.class.isAssignableFrom(fieldClass)) {
					Class<?> collectionType = getCollectionGenericType(fieldTemp);
					SmartField smartFieldNewItem = new SmartField(smartField, collectionType, fieldTemp.getName() + "[0]");
					smartField.addChild(smartFieldNewItem);
					lookup(smartFieldNewItem);
				}
			}
		}
	}

	public void fill(SmartField smartField) {
		Object value = defaultMap.get(smartField.getClazz());
		if (value == null) {
			value = newInstance.newInstance(smartField.getClazz());
			defaultMap.put(smartField.getClazz(), value);
		}
		smartField.setValue(value);

		if (smartField.getParent() != null) {
			System.out.println(smartField.getPath());
			try {
				if (smartField.getPath().endsWith("]")) {
					PropertyUtils.setIndexedProperty(smartField.getParent().getValue(), smartField.getPath(), smartField.getValue());
				} else {
					PropertyUtils.setProperty(smartField.getParent().getValue(), smartField.getPath(), smartField.getValue());
					if (Collection.class.isAssignableFrom(smartField.getClazz()))
						((Collection<?>) smartField.getValue()).add(null);
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		List<SmartField> children = smartField.getChildren();
		if (children != null) {
			for (SmartField smartFieldTemp : children)
				fill(smartFieldTemp);
		}
	}

	private Class<?> getCollectionGenericType(Field field) {
		Object obj = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
		if (obj instanceof Class<?>) {
			return (Class<?>) obj;
		} else if (obj instanceof WildcardType) {
			return Object.class;
		} else {
			throw new RuntimeException("Collection generic type problem");
		}
	}
}
