package test;

import javax.persistence.Persistence;

import datatax.DtHistoricoTicket;
import datatax.DtParametro;
import datatax.DtPlaca;
import datatax.DtRestricao;
import datatax.DtSituacaoTicket;
import datatax.DtTicket;
import datatax.DtUnidadeOperacional;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestDataTax extends TestCase {

	JPAMock jpaMock;

	@Override protected void setUp() throws Exception {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
	}

	public void test() {
		jpaMock.clearAll();
		jpaMock.mock(DtHistoricoTicket.class);
		jpaMock.mock(DtParametro.class);
		jpaMock.mock(DtPlaca.class);
		jpaMock.mock(DtRestricao.class);
		jpaMock.mock(DtSituacaoTicket.class);
		jpaMock.mock(DtTicket.class);
		jpaMock.mock(DtUnidadeOperacional.class);
	}
}
