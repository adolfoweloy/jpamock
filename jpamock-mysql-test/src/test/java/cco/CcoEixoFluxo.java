package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import lombok.Getter;
import lombok.Setter;

@Entity public class CcoEixoFluxo {

	@Id @Column(name = "EIXO_FLUXO_ID") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "NUMERO") private @Getter @Setter String numero;

	@Column(name = "PESO") private @Getter @Setter Double peso;

	@Column(name = "DISTANCIA_PROXIMO_EIXO") private @Getter @Setter Double distanciaProximoEixo;

	@ManyToOne @JoinColumn(name = "FLUXO_ID") private @Getter @Setter CcoFluxo fluxo;

	public CcoEixoFluxo() {
	}

	public CcoEixoFluxo(String numero) {
		this.numero = numero;
	}

}