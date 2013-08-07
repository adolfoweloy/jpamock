package jpamock.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflection {

	private static Reflection reflection = new Reflection();

	private Reflection() {

	}

	public static Reflection get() {
		return reflection;
	}

	public void setProperty(Object obj, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setProperty(Object obj, String field, Object value) {
		try {
			setProperty(obj, obj.getClass().getDeclaredField(field), value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public Object getProperty(Object obj, Field field) {
		try {
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object getProperty(Object obj, String field) {
		try {
			return getProperty(obj, obj.getClass().getDeclaredField(field));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Field getField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			try {
				return clazz.getSuperclass().getDeclaredField(fieldName);
			} catch (Exception e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	public List<Field> getAllDeclaredFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		try {
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
		} catch (Exception e) {
			//throw new RuntimeException(e);
		}
		return fields;
	}

	public boolean isBeanType(Class<?> clazz) {
		return clazz.getClassLoader() != null;
	}

	public boolean isAnnotationPresent(Field field, Class<? extends Annotation> annotation) {
		if (field.isAnnotationPresent(annotation)) {
			return true;
		} else {
			try {
				String getterName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				Class<?>[] parameter = null;
				Method method = field.getDeclaringClass().getMethod(getterName, parameter);
				if (method.isAnnotationPresent(annotation))
					return true;
			} catch (Exception e) {
				try {
					String getterName = "is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
					Class<?>[] parameter = null;
					Method method = field.getDeclaringClass().getMethod(getterName, parameter);
					if (method.isAnnotationPresent(annotation))
						return true;
				} catch (Exception e1) {
					// no getter
				}
			}
		}
		return false;
	}

	public Annotation getAnnotation(Field field, Class<? extends Annotation> annotation) {
		if (field.isAnnotationPresent(annotation)) {
			return field.getAnnotation(annotation);
		} else {
			try {
				String getterName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				Class<?>[] parameter = null;
				Method method = field.getDeclaringClass().getMethod(getterName, parameter);
				if (method.isAnnotationPresent(annotation))
					return method.getAnnotation(annotation);
			} catch (Exception e) {
				try {
					String getterName = "is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
					Class<?>[] parameter = null;
					Method method = field.getDeclaringClass().getMethod(getterName, parameter);
					if (method.isAnnotationPresent(annotation))
						return method.getAnnotation(annotation);
				} catch (Exception e1) {
					// no getter
				}
			}
		}
		return null;
	}

}
