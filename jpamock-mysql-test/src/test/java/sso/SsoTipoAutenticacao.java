package sso;

import lombok.Getter;

public enum SsoTipoAutenticacao {

	DATABASE(1L), LDAP(2L);

	private @Getter Long value;

	private SsoTipoAutenticacao(Long value) {
		this.value = value;
	}

}
