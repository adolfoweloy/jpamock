package fiscal;


public enum FiscalAcaoCuidadoEspecialEnum {
	
	LIVRE(1L), ALERTA(2L);

	FiscalAcaoCuidadoEspecialEnum(Long value) {
		this.value = value;
	}


	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public boolean compareTo(FiscalAcaoTicket acaoTicket) {
		return acaoTicket.getId().equals(this.getValue()) ? true : false;
	}

}
