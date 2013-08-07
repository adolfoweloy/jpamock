package test;

import restricao.ResPessoaFisica;
import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestInstance extends TestCase {

	JPAMock jpaMock = new JPAMock(null);

	public void test() {
		ResPessoaFisica resPessoaFisica = jpaMock.mock(ResPessoaFisica.class);
		assertNotNull(resPessoaFisica.getVeiculos().get(0).getPessoa().getNome());
	}


}
