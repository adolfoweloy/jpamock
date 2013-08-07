package datatax;

import lombok.Getter;

public enum DtRestricaoEnum {

	FUGA(7L), FUGA_ANTERIOR(8L), ABERTURA_MANUAL(13L);

	private @Getter Long value;

	private DtRestricaoEnum(Long value) {
		this.value = value;
	}

}
