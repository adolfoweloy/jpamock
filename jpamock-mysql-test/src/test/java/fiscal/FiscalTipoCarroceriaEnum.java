package fiscal;


public enum FiscalTipoCarroceriaEnum {

	CONTAINER(1L), CARGA_SECA(2L), CEGONHA(3L), BAU_SIMPLES(4L), BAU_FRIGORIFICO(5L);

	FiscalTipoCarroceriaEnum(Long value) {
		this.value = value;
	}


	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}
	
	public boolean compareTo(FiscalTipoCarroceria tipoCarroceria) {
		return tipoCarroceria.getId().equals(this.getValue()) ? true : false;
	}

}