package fiscal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBHORA_AJUSTE_PESO")
public class FiscalHoraAjustePeso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "HORA_AJUSTE_PESO_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue
	private Long id;

	@Column(name = "PERCENTUAL_AJUSTE")
	private Double percentualAjuste;

	@Column(name = "HORA_AJUSTE")
	private Integer horaAjuste;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASSIFICACAO_ID")
	private FiscalClassificacao classificacao;

	public FiscalHoraAjustePeso() {
	}

	public FiscalHoraAjustePeso(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPercentualAjuste() {
		return percentualAjuste;
	}

	public void setPercentualAjuste(Double percentualAjuste) {
		this.percentualAjuste = percentualAjuste;
	}

	public Integer getHoraAjuste() {
		return horaAjuste;
	}

	public void setHoraAjuste(Integer horaAjuste) {
		this.horaAjuste = horaAjuste;
	}

	public FiscalClassificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(FiscalClassificacao classificacao) {
		this.classificacao = classificacao;
	}

}