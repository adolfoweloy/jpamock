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

	public static FluentApi get() {
		FluentApi fluentApi = new FluentApi();
		string(fluentApi);
		primitiveAndWrappers(fluentApi);
		bigdecimal(fluentApi);
		date(fluentApi);
		calendar(fluentApi);
		sqlDate(fluentApi);
		time(fluentApi);
		timeStamp(fluentApi);
		collections(fluentApi);
		maps(fluentApi);
		arrays(fluentApi);
		return fluentApi;
	}

	private static void timeStamp(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(Timestamp.class)).putValue(new Timestamp(0));
	}

	private static void time(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(Time.class)).putValue(new Time(0));
	}

	private static void sqlDate(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(java.sql.Date.class)).putValue(new java.sql.Date(0));
	}

	private static void calendar(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(Calendar.class)).putValue(getCalendarDate0());
	}

	private static void date(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(Date.class)).putValue(new Date(0));
	}

	private static void bigdecimal(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(BigInteger.class)).putValue(new BigInteger("0"));
		fluentApi.putKey(new SmartClass(BigDecimal.class)).putValue(new BigDecimal("0"));
	}

	private static void arrays(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(byte[].class)).putValue(new byte[] { 0x00 });
		fluentApi.putKey(new SmartClass(char[].class)).putValue(new char[] { '0' });
		fluentApi.putKey(new SmartClass(Byte[].class)).putValue(new Byte[] { 0x00 });
		fluentApi.putKey(new SmartClass(Character[].class)).putValue(new Character[] { '0' });
	}

	private static void maps(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(Map.class)).putValue(new HashMap<Object, Object>());
		fluentApi.putKey(new SmartClass(HashMap.class)).putValue(new HashMap<Object, Object>());
		fluentApi.putKey(new SmartClass(LinkedHashMap.class)).putValue(new LinkedHashMap<Object, Object>());
	}

	private static void primitiveAndWrappers(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(boolean.class)).putValue(new Boolean(false));
		fluentApi.putKey(new SmartClass(byte.class)).putValue(new Byte((byte) 0));
		fluentApi.putKey(new SmartClass(char.class)).putValue(new Character((char) 0));
		fluentApi.putKey(new SmartClass(short.class)).putValue(new Short((short) 0));
		fluentApi.putKey(new SmartClass(int.class)).putValue(0);
		fluentApi.putKey(new SmartClass(long.class)).putValue(0L);
		fluentApi.putKey(new SmartClass(float.class)).putValue(0F);
		fluentApi.putKey(new SmartClass(double.class)).putValue(0D);
		fluentApi.putKey(new SmartClass(Boolean.class)).putValue(new Boolean(false));
		fluentApi.putKey(new SmartClass(Byte.class)).putValue(new Byte((byte) 0));
		fluentApi.putKey(new SmartClass(Character.class)).putValue(new Character((char) 0));
		fluentApi.putKey(new SmartClass(Short.class)).putValue(new Short((short) 0));
		fluentApi.putKey(new SmartClass(Integer.class)).putValue(0);
		fluentApi.putKey(new SmartClass(Long.class)).putValue(0L);
		fluentApi.putKey(new SmartClass(Float.class)).putValue(0F);
		fluentApi.putKey(new SmartClass(Double.class)).putValue(0D);
	}

	private static void collections(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(Collection.class)).putValue(new ArrayList<Object>());
		fluentApi.putKey(new SmartClass(List.class)).putValue(new ArrayList<Object>());
		fluentApi.putKey(new SmartClass(ArrayList.class)).putValue(new ArrayList<Object>());
		fluentApi.putKey(new SmartClass(LinkedList.class)).putValue(new LinkedList<Object>());
		fluentApi.putKey(new SmartClass(Set.class)).putValue(new HashSet<Object>());
		fluentApi.putKey(new SmartClass(HashSet.class)).putValue(new HashSet<Object>());
		fluentApi.putKey(new SmartClass(LinkedHashSet.class)).putValue(new LinkedHashSet<Object>());
	}

	private static void string(FluentApi fluentApi) {
		fluentApi.putKey(new SmartClass(String.class)).putValue("abc");
	}

	private static Calendar getCalendarDate0() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(0));
		return calendar;
	}

}
