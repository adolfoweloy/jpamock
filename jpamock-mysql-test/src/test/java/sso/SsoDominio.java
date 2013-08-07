package sso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity public class SsoDominio implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TIPO_AUTENTICACAO = "TIPO_AUTENTICACAO";

	@Id @Column(name = "DOMINIO_ID") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "NOME") private @Getter @Setter String nome;

	@Column(name = "VALOR") private @Getter @Setter String valor;

	@Column(name = "DESCRICAO") private @Getter @Setter String descricao;

	/*
	 * CONSTRUCTORS, EQUALS AND HASHCODE
	 * -----------------------------------------------------------------------------------
	 */

	public SsoDominio() {
	}

	public SsoDominio(Long id) {
		this.id = id;
	}

	public SsoDominio(String nome, String valor, String descricao) {
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
	}

}