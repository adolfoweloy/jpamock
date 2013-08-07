package test;

import javax.persistence.Persistence;

import sso.SsoDominio;
import sso.SsoFuncionalidade;
import sso.SsoPerfil;
import sso.SsoSistema;
import sso.SsoUnidadeOperacional;
import sso.SsoUsuario;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestSSO extends TestCase {

	JPAMock jpaMock;

	@Override protected void setUp() throws Exception {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
	}

	public void test() {
		jpaMock.clearAll();
		jpaMock.mock(SsoDominio.class);
		jpaMock.mock(SsoFuncionalidade.class);
		jpaMock.mock(SsoPerfil.class);
		jpaMock.mock(SsoSistema.class);
		jpaMock.mock(SsoUnidadeOperacional.class);
		jpaMock.mock(SsoUsuario.class);
	}

}
