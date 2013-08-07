package test;

import java.util.List;
import java.util.Set;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestForceNull extends TestCase {

	JPAMock jpaMock = new JPAMock(null);

	public void testSetupNullField() {
		Sample sample1 = jpaMock.when(Sample.class, "str2").thenInject(null).mock(Sample.class);
		assertEquals("abc", sample1.str1);
		assertNull(sample1.str2);
	}

	public void testSetupNullClass() {
		jpaMock.when(SampleOther.class).thenInject(null);
		jpaMock.when(String.class).thenInject(null);
		Sample sample1 = jpaMock.mock(Sample.class);
		assertNull(sample1.sampleOther);
		assertNull(sample1.str1);
		assertNull(sample1.str2);
		assertNull(sample1.strList1);
		assertNull(sample1.strList2);
	}

	static public class Sample {
		String str1;
		String str2;
		List<String> strList1;
		List<String> strList2;
		List<Integer> integerList1;
		List<Integer> integerList2;
		int testePrimitive;
		Integer testeWrapper;
		Set<?> setTest;
		SampleOther sampleOther;
	}

	static public class SampleOther {
		String strOther1;
		String strOther2;
		SampleFinal sampleFinal;
	}

	static public class SampleFinal {
		String strFinal1;
		String strFinal2;
	}

}
