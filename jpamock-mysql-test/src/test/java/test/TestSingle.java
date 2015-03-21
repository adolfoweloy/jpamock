package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;
import cadastro.Pessoa;
import cadastro.Telefone;

public class TestSingle extends TestCase {

	static JPAMock jpaMock;

	static {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
		jpaMock.clearAll();
	}

	public void test() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		Telefone t1 = new Telefone();
		t1.setCodigoArea(61);
		t1.setTelefone("30312569");
		Telefone t2 = new Telefone();
		t2.setCodigoArea(61);
		t2.setTelefone("91256987");
		telefones.add(t1);
		telefones.add(t2);
		jpaMock.when(Pessoa.class, "telefones");
		jpaMock.thenInject(telefones);
		jpaMock.mock(Pessoa.class);
	}

}
