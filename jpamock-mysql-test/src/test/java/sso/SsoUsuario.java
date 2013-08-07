package sso;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity public class SsoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Long ID_USUARIO_ADMIN = 1L;

	@Id @Column(name = "USUARIO_ID") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "LOGIN") private @Getter @Setter String login;

	@Column(name = "PASSWORD") private @Getter @Setter String senha;

	@Column(name = "NOME") private @Getter @Setter String nome;

	@Column(name = "EMAIL") private @Getter @Setter String email;

	@Column(name = "TIPO_AUTENTICACAO") private @Getter @Setter @Enumerated(EnumType.ORDINAL) SsoTipoAutenticacao tipoAutenticacao;

	@ManyToOne @JoinColumn(name = "UNIDADE_OPERACIONAL_ID") private @Getter @Setter SsoUnidadeOperacional unidadeOperacional;

	@ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "SsoPefilUsuario", joinColumns = { @JoinColumn(name = "USUARIO_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERFIL_ID") }) private @Getter @Setter List<SsoPerfil> perfis;

	/*
	 * CONSTRUCTORS, EQUALS AND HASHCODE
	 * -----------------------------------------------------------------------------------
	 */

	public SsoUsuario() {
	}

	public SsoUsuario(Long id) {
		this.id = id;
	}

	public SsoUsuario(String login, String senha, String nome, String email, SsoTipoAutenticacao tipoAutenticacao, SsoUnidadeOperacional unidadeOperacional) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.tipoAutenticacao = tipoAutenticacao;
		this.unidadeOperacional = unidadeOperacional;
	}

}