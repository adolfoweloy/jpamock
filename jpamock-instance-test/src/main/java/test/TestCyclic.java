package test;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestCyclic extends TestCase {

	JPAMock jpaMock = new JPAMock(null);

	public void testCyclic() {
		Cyclic1 cyclic1 = jpaMock.mock(Cyclic1.class);
		assertSame(cyclic1, cyclic1.cyclic2.cyclic1);
	}

	static public class Cyclic1 {
		Cyclic2 cyclic2;
	}

	static public class Cyclic2 {
		Cyclic1 cyclic1;
	}

}
