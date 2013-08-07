package test;

import javax.persistence.Persistence;

import cco.CcoClassificacao;
import cco.CcoCorVeiculo;
import cco.CcoEixoFluxo;
import cco.CcoEquipamento;
import cco.CcoEspecieVeiculo;
import cco.CcoFaixa;
import cco.CcoFluxo;
import cco.CcoImagem;
import cco.CcoLocal;
import cco.CcoMarcaModeloVeiculo;
import cco.CcoMunicipio;
import cco.CcoRestricao;
import cco.CcoRestricaoFluxo;
import cco.CcoTipoEquipamento;
import cco.CcoTipoVeiculo;
import cco.CcoUF;
import cco.CcoUnidadeOperacional;
import cco.CcoUsuario;
import cco.CcoVeiculo;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestCco extends TestCase {

	JPAMock jpaMock;

	@Override protected void setUp() throws Exception {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
	}

	public void test() {
		jpaMock.clearAll();
		jpaMock.mock(CcoClassificacao.class);
		jpaMock.mock(CcoCorVeiculo.class);
		jpaMock.mock(CcoEixoFluxo.class);
		jpaMock.mock(CcoEquipamento.class);
		jpaMock.mock(CcoEspecieVeiculo.class);
		jpaMock.mock(CcoFaixa.class);
		jpaMock.mock(CcoFluxo.class);
		jpaMock.mock(CcoImagem.class);
		jpaMock.mock(CcoLocal.class);
		jpaMock.mock(CcoMarcaModeloVeiculo.class);
		jpaMock.mock(CcoMunicipio.class);
		jpaMock.mock(CcoRestricao.class);
		jpaMock.mock(CcoRestricaoFluxo.class);
		jpaMock.mock(CcoTipoEquipamento.class);
		jpaMock.mock(CcoTipoVeiculo.class);
		jpaMock.mock(CcoUF.class);
		jpaMock.mock(CcoUnidadeOperacional.class);
		jpaMock.mock(CcoUsuario.class);
		jpaMock.mock(CcoVeiculo.class);
	}

}
