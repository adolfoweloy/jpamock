package fiscal;


public enum FiscalTipoImagemEnum {

	ZOOM(1L), PANORAMICA(2L);

	FiscalTipoImagemEnum(Long value) {
		this.value = value;
	}

	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public boolean compareTo(FiscalTipoImagem tipoImagem) {
		return tipoImagem.getId().equals(this.getValue()) ? true : false;
	}

}
