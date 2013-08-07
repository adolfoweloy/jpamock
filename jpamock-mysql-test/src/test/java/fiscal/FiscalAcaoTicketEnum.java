package fiscal;


public enum FiscalAcaoTicketEnum {

	NENHUM(1L), ABERTURA(2L), REABERTURA_DISPLAY(3L), FECHAMENTO(4L);

	FiscalAcaoTicketEnum(Long value) {
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