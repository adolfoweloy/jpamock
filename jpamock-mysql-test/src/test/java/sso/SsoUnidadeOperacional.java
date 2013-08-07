package sso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity public class SsoUnidadeOperacional implements Serializable {

	public static Long ID_UNIDADE_PRINCIPAL = 1L;

	private static final long serialVersionUID = 1L;

	@Id @Column(name = "UNIDADE_OPERACIONAL_ID") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "DESCRICAO") private @Getter @Setter String descricao;

	@ManyToOne @JoinColumn(name = "UNIDADE_AGRUPADORA_ID") private @Getter @Setter SsoUnidadeOperacional unidadeAgrupadora;

	/*
	 * CONSTRUCTORS, EQUALS AND HASHCODE
	 * -----------------------------------------------------------------------------------
	 */

	public SsoUnidadeOperacional() {
	}

	public SsoUnidadeOperacional(Long id) {
		this.id = id;
	}

	public SsoUnidadeOperacional(String descricao, SsoUnidadeOperacional unidadeAgrupadora) {
		this.descricao = descricao;
		this.unidadeAgrupadora = unidadeAgrupadora;
	}



}
