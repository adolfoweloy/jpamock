package fiscal;


public enum FiscalTipoUnidadeFiscalEnum {

	FIXA(1L), MOVEL(2L);

	FiscalTipoUnidadeFiscalEnum(Long value) {
		this.value = value;
	}

	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}
	
	public boolean compareTo(FiscalTipoUnidadeFiscal tipoUnidadeFiscal) {
		return tipoUnidadeFiscal.getId().equals(this.getValue()) ? true : false;
	}

}