package sso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity public class SsoPerfil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Long ID_PERFIL_ADMINISTRADOR_SSO = 1L;

	@Id @Column(name = "PERFIL_ID") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "DESCRICAO") private @Getter @Setter String descricao;

	@ManyToOne @JoinColumn(name = "SISTEMA_ID") private @Getter @Setter SsoSistema sistema;

	@ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "SsoFuncionalidadePerfil", joinColumns = { @JoinColumn(name = "PERFIL_ID") }, inverseJoinColumns = { @JoinColumn(name = "FUNCIONALIDADE_ID") }) private @Getter @Setter List<SsoFuncionalidade> funcionalidades = new ArrayList<SsoFuncionalidade>();

	@ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "SsoPerfilUsuario", joinColumns = { @JoinColumn(name = "PERFIL_ID") }, inverseJoinColumns = { @JoinColumn(name = "USUARIO_ID") }) private @Getter @Setter List<SsoUsuario> usuarios = new ArrayList<SsoUsuario>();

	/*
	 * CONSTRUCTORS, EQUALS AND HASHCODE
	 * -----------------------------------------------------------------------------------
	 */

	public SsoPerfil() {
	}

	public SsoPerfil(Long id) {
		this.id = id;
	}

	public SsoPerfil(String descricao, SsoSistema sistema) {
		this.descricao = descricao;
		this.sistema = sistema;
	}


}