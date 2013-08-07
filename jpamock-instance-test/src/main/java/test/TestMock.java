package test;

import java.util.List;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestMock extends TestCase {

	JPAMock jpaMock = new JPAMock(null);

	public void testSetupPrimitive() {
		assertEquals(new Integer(0), jpaMock.mock(int.class));
		assertEquals(new Integer(0), jpaMock.mock(Integer.class));
		assertEquals(0, jpaMock.mock(Sample.class).testePrimitive);
		assertEquals(new Integer(0), jpaMock.mock(Sample.class).testeWrapper);
	}

	public void testSetupOverride() {
		jpaMock.when(String.class).thenInject("  ");
		assertEquals("  ", jpaMock.mock(String.class));
		Sample sample1 = jpaMock.mock(Sample.class);
		assertEquals("  ", sample1.str1);
		assertEquals("  ", sample1.str2);
		assertEquals("  ", sample1.strList1.get(0));
		assertEquals("  ", sample1.strList2.get(0));
		Sample sample = new Sample();
		sample.str1 = "abc";
		Sample sample2 = jpaMock.when(Sample.class).thenInject(sample).mock(Sample.class);
		assertEquals("abc", sample2.str1);
		assertEquals("  ", sample2.strList1.get(0));
		assertEquals("  ", sample2.strList2.get(0));
		jpaMock.when(String.class).thenInject("xyz");
		Sample sample3 = jpaMock.mock(Sample.class);
		assertEquals("abc", sample3.str1);
		assertEquals("xyz", sample3.str2);
		assertEquals("xyz", sample3.strList1.get(0));
		assertEquals("xyz", sample3.strList2.get(0));
	}

	public void testSetupThree() {
		Sample sample = new Sample();
		SampleOther sampleOther = new SampleOther();
		SampleFinal sampleFinal = new SampleFinal();

		sample.str2 = "cba";
		sample.sampleOther = sampleOther;
		sampleOther.strOther2 = "xyz";
		sampleOther.sampleFinal = sampleFinal;
		sampleFinal.strFinal2 = "ttt";

		Sample sample1 = jpaMock.when(Sample.class).thenInject(sample).mock(Sample.class);
		assertEquals("abc", sample1.str1);
		assertEquals("cba", sample1.str2);
		assertEquals("abc", sample1.sampleOther.strOther1);
		assertEquals("xyz", sample1.sampleOther.strOther2);
		assertEquals("abc", sample1.sampleOther.sampleFinal.strFinal1);
		assertEquals("ttt", sample1.sampleOther.sampleFinal.strFinal2);

	}

	public void testSetupList() {
		assertEquals("abc", jpaMock.mock(String.class));
		Sample sample1 = jpaMock.mock(Sample.class);
		assertEquals("abc", sample1.str1);
		assertEquals("abc", sample1.str2);
		assertEquals("abc", sample1.strList1.get(0));
		assertEquals("abc", sample1.strList2.get(0));
		assertEquals(new Integer(0), sample1.integerList1.get(0));
		assertEquals(new Integer(0), sample1.integerList2.get(0));
		assertEquals(1, sample1.strList1.size());
		assertEquals(1, sample1.strList1.size());
		assertEquals(1, sample1.integerList1.size());
		assertEquals(1, sample1.integerList2.size());
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
