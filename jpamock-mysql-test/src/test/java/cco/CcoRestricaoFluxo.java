package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoRestricaoFluxo {

	@Id
	@Column(name="RESTRICAO_FLUXO_ID")
	@GeneratedValue
	private @Getter @Setter Long id;
	
	@Column(name="TEXTO")
	private @Getter @Setter String texto;
	
	@ManyToOne
	@JoinColumn(name="RESTRICAO_ID")
	private @Getter @Setter CcoRestricao restricao;
	
	@ManyToOne
	@JoinColumn(name="FLUXO_ID")
	private @Getter @Setter CcoFluxo fluxo;
	
	public CcoRestricaoFluxo() {}
	
	public CcoRestricaoFluxo(CcoRestricao restricao, String texto) {
		this.restricao = restricao;
		this.texto = texto;
	}
	

		
}