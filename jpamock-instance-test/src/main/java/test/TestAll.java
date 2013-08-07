package test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestAll extends TestSuite {

	public static Test suite() throws ClassNotFoundException {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestForceNull.class);
		suite.addTestSuite(TestInheritance.class);
		suite.addTestSuite(TestMock.class);
		suite.addTestSuite(TestCyclic.class);
		suite.addTestSuite(TestSample.class);
		return suite;
	}

}