package datatax;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity public class DtTicket {

	@Id @GeneratedValue protected @Getter @Setter Long id;
	@Column(name = "PLACA") protected @Getter String placa;
	@Column(name = "DATA_CRIACAO") protected @Getter @Setter Date dataCriacao;
	@Column(name = "DATA_FECHAMENTO") protected @Getter @Setter Date dataFechamento;
	@Column(name = "DATA_ALTERACAO") protected @Getter @Setter Date dataAlteracao;
	@Column(name = "PESO_SIWIM") protected @Getter @Setter Double pesoSiwim;
	@Column(name = "TARA") protected @Getter @Setter Double tara;
	@Column(name = "PESO_DECLARADO_NF") protected @Getter @Setter Double pesoDeclaradoNF;
	@Column(name = "TARA_DECLARADA_CAMINHAO") protected @Getter @Setter Double taraDeclaradaCaminhao;
	@Column(name = "PESO_BALANCA_ESTATICA") protected @Getter @Setter Double pesoBalancaEstatica;
	@Column(name = "TARA_BALANCA_ESTATICA") protected @Getter @Setter Double taraBalancaEstatica;

	@Column(name = "USUARIO_ID") protected @Getter @Setter Long idUsuario;

	@OneToOne @JoinColumn(name = "SITUACAO_ATUAL_TICKET") protected @Getter @Setter DtSituacaoTicket situacaoTicketAtual;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) protected @Getter @Setter List<DtHistoricoTicket> situacaoTicketList;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) @JoinTable(name = "DTTICKET_RESTRICAO", joinColumns = { @JoinColumn(name = "TICKET_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESTRICAO_ID") }) protected @Getter @Setter List<DtRestricao> restricaoList;

	@ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "UNIDADE_OPERACIONAL_ID") protected @Getter @Setter DtUnidadeOperacional unidadeOperacional;

}
