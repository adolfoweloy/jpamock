package test;

import javax.persistence.Persistence;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;
import fiscal.FiscalAcaoCuidadoEspecial;
import fiscal.FiscalAcaoTicket;
import fiscal.FiscalAlerta;
import fiscal.FiscalClassificacao;
import fiscal.FiscalCuidadoEspecial;
import fiscal.FiscalEixo;
import fiscal.FiscalEquipamento;
import fiscal.FiscalEquipamentoFaixa;
import fiscal.FiscalFlagPesagem;
import fiscal.FiscalFlagPesagemTrafego;
import fiscal.FiscalFuncionalidade;
import fiscal.FiscalHistoricoTicket;
import fiscal.FiscalHoraAjustePeso;
import fiscal.FiscalImagem;
import fiscal.FiscalImagemTrafego;
import fiscal.FiscalInfracao;
import fiscal.FiscalLocal;
import fiscal.FiscalMarcaModelo;
import fiscal.FiscalMensagemAdHoc;
import fiscal.FiscalMotivo;
import fiscal.FiscalMunicipio;
import fiscal.FiscalParticipante;
import fiscal.FiscalPerfil;
import fiscal.FiscalPermissao;
import fiscal.FiscalPermissaoOperador;
import fiscal.FiscalPermissaoPerfil;
import fiscal.FiscalPostoFiscal;
import fiscal.FiscalResponsavelVeiculo;
import fiscal.FiscalSituacaoPesagem;
import fiscal.FiscalSituacaoTicket;
import fiscal.FiscalTicket;
import fiscal.FiscalTicketMotivo;
import fiscal.FiscalTipoCarroceria;
import fiscal.FiscalTipoEquipamento;
import fiscal.FiscalTipoImagem;
import fiscal.FiscalTipoMercadoria;
import fiscal.FiscalTipoServico;
import fiscal.FiscalTipoUnidadeFiscal;
import fiscal.FiscalTrafego;
import fiscal.FiscalUnidadeFiscal;
import fiscal.FiscalUsuario;
import fiscal.FiscalVeiculo;
import fiscal.FiscalVeiculoCuidadoEspecial;

public class TestFiscal extends TestCase {

	JPAMock jpaMock;

	@Override protected void setUp() throws Exception {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
	}

	public void test() throws ClassNotFoundException {
		jpaMock.clearAll();
		jpaMock.mock(FiscalAcaoCuidadoEspecial.class);
		jpaMock.mock(FiscalAcaoTicket.class);
		jpaMock.mock(FiscalAlerta.class);
		jpaMock.mock(FiscalClassificacao.class);
		jpaMock.mock(FiscalCuidadoEspecial.class);
		jpaMock.mock(FiscalEixo.class);
		jpaMock.mock(FiscalEquipamento.class);
		jpaMock.mock(FiscalEquipamentoFaixa.class);
		jpaMock.mock(FiscalFlagPesagem.class);
		jpaMock.mock(FiscalFlagPesagemTrafego.class);
		jpaMock.mock(FiscalFuncionalidade.class);
		jpaMock.mock(FiscalHistoricoTicket.class);
		jpaMock.mock(FiscalHoraAjustePeso.class);
		jpaMock.mock(FiscalImagem.class);
		jpaMock.mock(FiscalImagemTrafego.class);
		jpaMock.mock(FiscalInfracao.class);
		jpaMock.mock(FiscalLocal.class);
		jpaMock.mock(FiscalMarcaModelo.class);
		jpaMock.mock(FiscalMensagemAdHoc.class);
		jpaMock.mock(FiscalMotivo.class);
		jpaMock.mock(FiscalMunicipio.class);
		jpaMock.mock(FiscalParticipante.class);
		jpaMock.mock(FiscalPerfil.class);
		jpaMock.mock(FiscalPermissao.class);
		jpaMock.mock(FiscalPermissaoOperador.class);
		jpaMock.mock(FiscalPermissaoPerfil.class);
		jpaMock.mock(FiscalPostoFiscal.class);
		jpaMock.mock(FiscalResponsavelVeiculo.class);
		jpaMock.mock(FiscalSituacaoPesagem.class);
		jpaMock.mock(FiscalSituacaoTicket.class);
		jpaMock.mock(FiscalTicket.class);
		jpaMock.mock(FiscalTicketMotivo.class);
		jpaMock.mock(FiscalTipoCarroceria.class);
		jpaMock.mock(FiscalTipoEquipamento.class);
		jpaMock.mock(FiscalTipoImagem.class);
		jpaMock.mock(FiscalTipoMercadoria.class);
		jpaMock.mock(FiscalTipoServico.class);
		jpaMock.mock(FiscalTipoUnidadeFiscal.class);
		jpaMock.mock(FiscalTrafego.class);
		jpaMock.mock(FiscalUnidadeFiscal.class);
		jpaMock.mock(FiscalUsuario.class);
		jpaMock.mock(FiscalVeiculo.class);
		jpaMock.mock(FiscalVeiculoCuidadoEspecial.class);
	}

}
