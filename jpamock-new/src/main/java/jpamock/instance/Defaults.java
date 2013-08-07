package jpamock.instance;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Defaults {

	public void fill(Map<Class<?>, Object> mapDefault) {
		string(mapDefault);
		primitiveAndWrappers(mapDefault);
		bigdecimal(mapDefault);
		date(mapDefault);
		calendar(mapDefault);
		sqlDate(mapDefault);
		time(mapDefault);
		timeStamp(mapDefault);
		collections(mapDefault);
		maps(mapDefault);
		arrays(mapDefault);
	}

	private  void timeStamp(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(Timestamp.class,new Timestamp(0));
	}

	private  void time(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(Time.class,new Time(0));
	}

	private  void sqlDate(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(java.sql.Date.class,new java.sql.Date(0));
	}

	private  void calendar(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(Calendar.class,getCalendarDate0());
	}

	private  void date(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(Date.class,new Date(0));
	}

	private  void bigdecimal(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(BigInteger.class,new BigInteger("0"));
		mapDefault.put(BigDecimal.class,new BigDecimal("0"));
	}

	private  void arrays(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(byte[].class,new byte[] { 0x00 });
		mapDefault.put(char[].class,new char[] { '0' });
		mapDefault.put(Byte[].class,new Byte[] { 0x00 });
		mapDefault.put(Character[].class,new Character[] { '0' });
	}

	private  void maps(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(Map.class,new HashMap<Object, Object>());
		mapDefault.put(HashMap.class,new HashMap<Object, Object>());
		mapDefault.put(LinkedHashMap.class,new LinkedHashMap<Object, Object>());
	}

	private  void primitiveAndWrappers(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(boolean.class,new Boolean(false));
		mapDefault.put(byte.class,new Byte((byte) 0));
		mapDefault.put(char.class,new Character((char) 0));
		mapDefault.put(short.class,new Short((short) 0));
		mapDefault.put(int.class,0);
		mapDefault.put(long.class,0L);
		mapDefault.put(float.class,0F);
		mapDefault.put(double.class,0D);
		mapDefault.put(Boolean.class,new Boolean(false));
		mapDefault.put(Byte.class,new Byte((byte) 0));
		mapDefault.put(Character.class,new Character((char) 0));
		mapDefault.put(Short.class,new Short((short) 0));
		mapDefault.put(Integer.class,0);
		mapDefault.put(Long.class,0L);
		mapDefault.put(Float.class,0F);
		mapDefault.put(Double.class,0D);
	}

	private  void collections(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(Collection.class,new ArrayList<Object>());
		mapDefault.put(List.class,new ArrayList<Object>());
		mapDefault.put(ArrayList.class,new ArrayList<Object>());
		mapDefault.put(LinkedList.class,new LinkedList<Object>());
		mapDefault.put(Set.class,new HashSet<Object>());
		mapDefault.put(HashSet.class,new HashSet<Object>());
		mapDefault.put(LinkedHashSet.class,new LinkedHashSet<Object>());
	}

	private  void string(Map<Class<?>, Object> mapDefault) {
		mapDefault.put(String.class,"abc");
	}

	private  Calendar getCalendarDate0() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(0));
		return calendar;
	}

}
