package test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestAll extends TestSuite {

	public static Test suite() throws ClassNotFoundException {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestDataTax.class);
		suite.addTestSuite(TestSSO.class);
		suite.addTestSuite(TestCco.class);
		//suite.addTestSuite(TestFiscal.class);
		suite.addTestSuite(TestRestricao.class);

		suite.addTestSuite(TestHidden.class);
		suite.addTestSuite(TestInstance.class);
		suite.addTestSuite(TestSingle.class);

		return suite;
	}

}