package jpamock.persitence;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import jpamock.reflection.PropertyDescription;

public class DataHelper {
	private SecureRandom random = new SecureRandom();
	
	public Object getData(final EntityManager entityManager, PropertyDescription propertyDescription, Field field) {
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
			
		}
		
		return object;
	}

}
