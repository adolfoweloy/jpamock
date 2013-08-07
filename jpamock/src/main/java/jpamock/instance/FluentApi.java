package jpamock.instance;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jpamock.reflection.PropertyCallBack;
import jpamock.reflection.PropertyDescription;
import jpamock.reflection.RecursiveSearch;
import jpamock.reflection.Reflection;

@SuppressWarnings("unchecked")
public class FluentApi {

	private Reflection reflection = Reflection.get();
	private SmartMap<SmartClass, Object> smartMap = new SmartMap<SmartClass, Object>();
	public Map<String, Object> specialFields = new HashMap<String, Object>();

	public Map<String, Object> getSpecialFields() {
		return specialFields;
	}

	// when
	public FluentApi putKey(SmartClass smartClass) {
		smartMap.put(smartClass, smartMap.remove(smartClass));
		return this;
	}

	// thenInject
	public void putValue(Object value) {
		if (value == null) {
			if (smartMap.getLastKey().getField() != null) {
				specialFields.put(smartMap.getLastKey().getField().toString() + "0", null);
			} else {
				specialFields.put(smartMap.getLastKey().getBeanType().toString(), null);
			}
		} else {
			if (smartMap.getLastKey().getField() == null)
				smartMap.put(smartMap.getLastKey(), value);
			new RecursiveSearch(new PropertyCallBack() {
				public void propertyCallBack(PropertyDescription propertyDescription) {
					Object value = propertyDescription.getParent();
					Field field = propertyDescription.getField();
					if (field != null) {
						Object fromMap = smartMap.get(new SmartClass(field));
						Object fromReal = reflection.getProperty(value, field);
						if (fromReal != null && !fromMap.equals(fromReal))
							specialFields.put(field.toString() + propertyDescription.getIndex(), fromReal);
					} else {
						Field fieldTemp = smartMap.getLastKey().getField();
						if (fieldTemp != null && fieldTemp.getType().isAssignableFrom(value.getClass()) && !specialFields.containsKey(fieldTemp.toString() + propertyDescription.getIndex()))
							specialFields.put(fieldTemp.toString() + propertyDescription.getIndex(), value);
					}
				}
			}).start(value);
		}
	}

	// mock
	public Object getValue(SmartClass smartClass) {
		Object value = smartMap.get(smartClass);
		new RecursiveSearch(new PropertyCallBack() {
			public void propertyCallBack(PropertyDescription propertyDescription) {
				Object value = propertyDescription.getParent();
				if (value.getClass().isEnum())
					return;
				Field field = propertyDescription.getField();
				if (field == null)
					return;
				SmartClass smartClassField = new SmartClass(field);
				Object property = smartMap.get(smartClassField);
				if (specialFields.containsKey(field.toString() + propertyDescription.getIndex()) || specialFields.containsKey(smartClassField.getBeanType().toString())) {
					reflection.setProperty(value, field, specialFields.get(field.toString() + propertyDescription.getIndex()));
				} else {
					reflection.setProperty(value, field, property);
					if (smartClassField.isCollection())
						((Collection<Object>) property).add(smartMap.get(new SmartClass(smartClassField.getBeanType())));
				}
			}
		}).start(value);
		return value;
	}

}
