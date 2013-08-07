package test;

import javax.persistence.Persistence;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;
import restricao.ResAjusteHoraPeso;
import restricao.ResCategoriaVeiculo;
import restricao.ResClassificacao;
import restricao.ResCorVeiculo;
import restricao.ResEquipamento;
import restricao.ResEquipamentoFaixa;
import restricao.ResEspecieVeiculo;
import restricao.ResFaixa;
import restricao.ResGrauInfracao;
import restricao.ResHistoricoEquipamento;
import restricao.ResHistoricoEquipamentoInfracaoFoco;
import restricao.ResHistoricoInfracao;
import restricao.ResInfracao;
import restricao.ResIrregularidade;
import restricao.ResLocal;
import restricao.ResMarcaModelo;
import restricao.ResMunicipio;
import restricao.ResPessoa;
import restricao.ResPessoaFisica;
import restricao.ResPessoaJuridica;
import restricao.ResPessoaVeiculo;
import restricao.ResProgramacao;
import restricao.ResRestricao;
import restricao.ResRestricaoAtributo;
import restricao.ResTipoCriptografia;
import restricao.ResTipoEquipamento;
import restricao.ResTipoFoco;
import restricao.ResTipoInfracao;
import restricao.ResTipoRestricao;
import restricao.ResTipoVeiculo;
import restricao.ResUF;
import restricao.ResUnidadeOperacional;
import restricao.ResVeiculo;

public class TestRestricao extends TestCase {

	JPAMock jpaMock;

	@Override protected void setUp() throws Exception {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
		jpaMock.when(String.class).thenInject("abc");
	}

	public void testAjusteHoraPeso() {
		jpaMock.clearAll();
		jpaMock.mock(ResAjusteHoraPeso.class);
		jpaMock.mock(ResCategoriaVeiculo.class);
		jpaMock.mock(ResClassificacao.class);
		jpaMock.mock(ResCorVeiculo.class);
		jpaMock.mock(ResEquipamento.class);
		jpaMock.mock(ResEquipamentoFaixa.class);
		jpaMock.mock(ResEspecieVeiculo.class);
		jpaMock.mock(ResFaixa.class);
		jpaMock.mock(ResGrauInfracao.class);
		jpaMock.mock(ResHistoricoEquipamento.class);
		jpaMock.mock(ResHistoricoEquipamentoInfracaoFoco.class);
		jpaMock.mock(ResHistoricoInfracao.class);
		jpaMock.mock(ResInfracao.class);
		jpaMock.mock(ResIrregularidade.class);
		jpaMock.mock(ResLocal.class);
		jpaMock.mock(ResMarcaModelo.class);
		jpaMock.mock(ResMunicipio.class);
		jpaMock.mock(ResPessoa.class);
		jpaMock.mock(ResPessoaFisica.class);
		jpaMock.mock(ResPessoaJuridica.class);
		jpaMock.mock(ResPessoaVeiculo.class);
		jpaMock.mock(ResProgramacao.class);
		jpaMock.mock(ResRestricao.class);
		jpaMock.mock(ResRestricaoAtributo.class);
		jpaMock.mock(ResTipoCriptografia.class);
		jpaMock.mock(ResTipoEquipamento.class);
		jpaMock.mock(ResTipoFoco.class);
		jpaMock.mock(ResTipoInfracao.class);
		jpaMock.mock(ResTipoRestricao.class);
		jpaMock.mock(ResTipoVeiculo.class);
		jpaMock.mock(ResUF.class);
		jpaMock.mock(ResUnidadeOperacional.class);
		jpaMock.mock(ResVeiculo.class);
	}

}
