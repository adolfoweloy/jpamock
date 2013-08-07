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

@SuppressWarnings("serial") @Entity
@Table(name = "TBFLAG_PESAGEM_TRAFEGO")
 public class FiscalFlagPesagemTrafego implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "TBFLAG_PESAGEM_TRAFEGO_ID", unique = true, nullable = false, precision = 38, scale = 0)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAFEGO_ID")
	private FiscalTrafego trafego;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FLAG_PESAGEM_ID")
	private FiscalFlagPesagem flagPesagem;

	public FiscalFlagPesagemTrafego() {
	}

	public FiscalFlagPesagemTrafego(Long id) {
		this.id = id;
	}

	public FiscalFlagPesagemTrafego(FiscalTrafego trafego, FiscalFlagPesagem flagPesagem) {
		this.trafego = trafego;
		this.flagPesagem = flagPesagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FiscalTrafego getTrafego() {
		return trafego;
	}

	public void setTrafego(FiscalTrafego trafego) {
		this.trafego = trafego;
	}

	public FiscalFlagPesagem getFlagPesagem() {
		return flagPesagem;
	}

	public void setFlagPesagem(FiscalFlagPesagem flagPesagem) {
		this.flagPesagem = flagPesagem;
	}

}