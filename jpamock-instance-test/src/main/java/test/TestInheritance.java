package test;

import test.TestMock.SampleFinal;
import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestInheritance extends TestCase {

	JPAMock jpaMock = new JPAMock(null);

	public void test() {
		jpaMock.when(Sample2.class, "strOther2").thenInject("cba");
		Sample2 sample2 = jpaMock.mock(Sample2.class);
		assertEquals(sample2.strOther1, "abc");
		assertEquals(sample2.strOther2, "cba");
	}

	static public class Sample1 {
		String strOther1;
		String strOther2;
		SampleFinal sampleFinal;
	}

	static public class Sample2 extends Sample1 {
		String strFinal1;
		String strFinal2;
	}

}
