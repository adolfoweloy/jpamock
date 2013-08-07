package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;



import lombok.Getter;
import lombok.Setter;

@Entity public class CcoImagem {

	@Id @Column(name = "ID_IMAGEM") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "SEQUENCIAL") private @Getter @Setter String sequencial;

	@Column(name = "IMAGEM_PATH") private @Getter @Setter String imagemPath;

	@Column(name = "TIPO_FOCO") private @Getter @Setter String tipoFoco;

	@ManyToOne @JoinColumn(name = "FLUXO_ID") private @Getter @Setter CcoFluxo fluxo;

	@Transient private @Getter @Setter byte[] bytes;

	public CcoImagem() {
	}

	public CcoImagem(String sequencial) {
		this.sequencial = sequencial;
	}

}