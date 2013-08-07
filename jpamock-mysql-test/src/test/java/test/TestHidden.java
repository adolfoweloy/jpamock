package test;

import hidden.HiddenBody;

import javax.persistence.Persistence;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestHidden extends TestCase {

	JPAMock jpaMock;

	@Override protected void setUp() throws Exception {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
	}

	public void test() {
		//jpaMock.clearAll();
		jpaMock.mock(HiddenBody.class);
	}

}
