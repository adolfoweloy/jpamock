package fiscal;

public enum FiscalAcaoAutuacaoEnum {
	NAO_INFORMADO(0L), AUTUADO(1L);

	FiscalAcaoAutuacaoEnum(Long value) {
		this.value = value;
	}

	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

}
