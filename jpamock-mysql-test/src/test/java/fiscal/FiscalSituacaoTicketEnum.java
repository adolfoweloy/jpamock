package fiscal;


public enum FiscalSituacaoTicketEnum {

	ABERTO(1L), FECHADO(2L), FECHADO_AUTOMATICO(3L);

	FiscalSituacaoTicketEnum(Long value) {
		this.value = value;
	}

	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public boolean compareTo(FiscalSituacaoTicket situacaoTicket) {
		return situacaoTicket.getId().equals(this.getValue()) ? true : false;
	}

}
