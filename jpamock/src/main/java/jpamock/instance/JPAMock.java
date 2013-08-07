package jpamock.instance;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import jpamock.persitence.JDBCPersistence;
import jpamock.reflection.Reflection;

@SuppressWarnings("unchecked")
public class JPAMock {

	private FluentApi fluentApi = Defaults.get();
	private Reflection reflection = Reflection.get();
	private JDBCPersistence jdbcPersistence;

	public JPAMock(EntityManagerFactory entityManagerFactory) {
		jdbcPersistence = new JDBCPersistence(entityManagerFactory);
	}

	public JPAMock when(Class<?> anyClass) {
		fluentApi.putKey(new SmartClass(anyClass));
		return this;
	}

	public JPAMock when(Class<?> anyClass, String fieldName) {
		fluentApi.putKey(new SmartClass(reflection.getField(anyClass, fieldName)));
		return this;
	}

	public JPAMock thenInject(Object any) {
		fluentApi.putValue(any);
		return this;
	}

	public <T> T mock(Class<T> any) {
		Object obj = fluentApi.getValue(new SmartClass(any));
		jdbcPersistence.persist(obj, fluentApi.getSpecialFields());
		return (T) obj;
	}

	public void clearAll() {
		jdbcPersistence.clearAll();
	}

	public void clear(Class<?> clazz) {
		throw new RuntimeException("Not implemented yet");
	}

	public boolean containsById(List<?> list, Object mock) {
		return jdbcPersistence.containsById(list, mock);
	}

	public boolean containsAllById(List<?> list1, List<?> list2) {
		return jdbcPersistence.containsAllById(list1, list2);
	}

}
