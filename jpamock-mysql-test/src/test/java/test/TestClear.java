package test;

import javax.persistence.Persistence;

import cco.CcoEixoFluxo;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestClear extends TestCase {

	JPAMock jpaMock;

	@Override protected void setUp() throws Exception {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
	}

	public void testClear() {
		jpaMock.mock(CcoEixoFluxo.class);
		jpaMock.clear(CcoEixoFluxo.class);
	}

	public void testClearAll() {
		jpaMock.clearAll();
	}

}
