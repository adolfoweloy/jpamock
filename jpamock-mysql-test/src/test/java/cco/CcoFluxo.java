package cco;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
public class CcoFluxo {
	
	@Id
	@Column(name="FLUXO_ID")
	@GeneratedValue
	private @Getter @Setter Long id;	
	
	@Column(name="PLACA")
	private @Getter @Setter String placa;
	
	@Column(name="DATA_HORA")
	private @Getter @Setter Date dataHora;
	
	@Column(name="VELOC_PERMITIDA")
	private @Getter @Setter Integer velocidadePermitida;
	
	@Column(name="VELOC_TOLERADA")
	private @Getter @Setter Integer velocidadeTolerada;
	
	@Column(name="VELOC_AFERIDA")
	private @Getter @Setter Integer velocidadeAferida;
	
	@Column(name="TARA")
	private @Getter @Setter Double tara;
	
	@Column(name="PESO_BRUTO")
	private @Getter @Setter Double pesoBruto;
	
	@Column(name="PESO_LIQUIDO")
	private @Getter @Setter Double pesoLiquido;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IMAGEM_PRINCIPAL")
	private @Getter @Setter CcoImagem imagemPrincipal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VEICULO_ID")	
	private @Getter @Setter CcoVeiculo veiculo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLASSIFICACAO_ID")
	private @Getter @Setter CcoClassificacao classificacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FAIXA_ID")
	private @Getter @Setter CcoFaixa faixa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LOCAL_ID")
	private @Getter @Setter CcoLocal local;
	
	@SuppressWarnings("deprecation") @OneToMany(fetch=FetchType.LAZY, mappedBy="fluxo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
	private @Getter @Setter List<CcoEixoFluxo> listEixoFluxo;
	
	@SuppressWarnings("deprecation") @OneToMany(fetch=FetchType.LAZY, mappedBy="fluxo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
	private @Getter @Setter List<CcoImagem> listImagem;
	
	@SuppressWarnings("deprecation") @OneToMany(fetch=FetchType.LAZY, mappedBy="fluxo")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
	private @Getter @Setter List<CcoRestricaoFluxo> listRestricaoFluxo;

	
}