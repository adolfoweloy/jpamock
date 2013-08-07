package fiscal;


public enum FiscalTipoEquipamentoEnum {

	PESAGEM_MOVEL(1L), PMV(2L), RADAR(3L);

	FiscalTipoEquipamentoEnum(Long value) {
		this.value = value;
	}


	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public boolean compareTo(FiscalTipoEquipamento tipoEquipamento) {
		return tipoEquipamento.getId().equals(this.getValue()) ? true : false;
	}
}