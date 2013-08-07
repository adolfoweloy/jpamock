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

@Entity public class SsoFuncionalidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @Column(name = "FUNCIONALIDADE_ID") @GeneratedValue private @Getter @Setter Long id;

	@Column(name = "DESCRICAO") private @Getter @Setter String descricao;

	@Column(name = "ALIAS_JAAS") private @Getter @Setter String aliasJaas;

	@ManyToOne @JoinColumn(name = "SISTEMA_ID") private @Getter @Setter SsoSistema sistema;

}