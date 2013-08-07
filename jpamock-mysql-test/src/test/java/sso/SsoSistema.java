package sso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity public class SsoSistema implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Long ID_SISTEMA_SSO = 1L;

	@Id @Column(name = "SISTEMA_ID") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "SIGLA") private @Getter @Setter String sigla;

	@Column(name = "DESCRICAO") private @Getter @Setter String descricao;

	@Column(name = "URL") private @Getter @Setter String url;

	@SuppressWarnings("deprecation") @OneToMany(fetch = FetchType.LAZY, mappedBy = "sistema") @Cascade( { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE_ORPHAN }) @OrderBy("descricao asc") private @Getter @Setter List<SsoFuncionalidade> funcionalidades = new ArrayList<SsoFuncionalidade>();

	@SuppressWarnings("deprecation") @OneToMany(fetch = FetchType.LAZY, mappedBy = "sistema") @Cascade( { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE_ORPHAN }) @OrderBy("descricao asc") private @Getter @Setter List<SsoPerfil> perfis = new ArrayList<SsoPerfil>();

	/*
	 * CONSTRUCTORS, EQUALS AND HASHCODE
	 * -----------------------------------------------------------------------------------
	 */

	public SsoSistema() {
	}

	public SsoSistema(Long id) {
		this.id = id;
	}

	public SsoSistema(String sigla, String descricao, String url) {
		this.sigla = sigla;
		this.descricao = descricao;
		this.url = url;
	}


}