package test;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestSimpleInstance extends TestCase {

	JPAMock jpaMock = new JPAMock(null);

	public void testSimple() {
		Sample sample = jpaMock.mock(Sample.class);
		assertNotNull(sample);
		assertNotNull(sample.str);
	}

	static public class Sample {

		private String str;

		public void setStr(String str) {
			this.str = str;
		}

		public String getStr() {
			return str;
		}
	}

}
