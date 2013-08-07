package jpamock.instance;

import java.util.HashMap;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;

public class JPAMock {

	NewInstance newInstance;
	Map<String, Class<?>> mapRooted = new HashMap<String, Class<?>>();
	Map<Class<?>, Object> defaultMap = new HashMap<Class<?>, Object>();

	public JPAMock(Object entityManagerFactory) {
		new Defaults().fill(defaultMap);
		newInstance = new NewInstance();
	}

	public JPAMock when(Class<?> anyClass) {
		return this;
	}

	public JPAMock when(Class<?> anyClass, String fieldName) {
		return this;
	}

	public JPAMock thenInject(Object any) {
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T mock(Class<T> any) {
		try {
			// Class<?> tempClass = hang(any);
			// Field tempField = tempClass.getDeclaredField("root");
			// Object value = newInstance.newInstance(any);
			// defaultMap.put(any, value);
			FieldLookup fieldLookup = new FieldLookup(newInstance, defaultMap);
			SmartField smartField = new SmartField(null, any, "");
			fieldLookup.lookup(smartField);
			fieldLookup.fill(smartField);
			return (T) smartField.getValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Class<?> hang(Class<?> any) {
		try {
			Class<?> rooted = null;
			ClassPool cp = ClassPool.getDefault();
			String newClassName = "jpamock.instance." + any.getSimpleName();
			rooted = mapRooted.get(newClassName);
			if (rooted == null) {
				CtClass root = cp.makeClass(newClassName);
				CtClass anyTemp = cp.get(any.getName());
				CtField f = new CtField(anyTemp, "root", root);
				root.addField(f);
				rooted = root.toClass();
				mapRooted.put(newClassName, rooted);
			}
			return rooted;
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

}
