package fiscal;


public enum FiscalSituacaoPesagemEnum {

	LIVRE(1L), FISCALIZAR(2L), AGUARDANDO_PESAGEM(3L), LIBERADO(4L), RETIDO(5L), FUGA(6L), AUTUADO(7L), PESAGEM_REALIZADA(8L), ATENDIMENTO(9L);

	FiscalSituacaoPesagemEnum(Long value) {
		this.value = value;
	}



	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public boolean compareTo(FiscalSituacaoPesagem situacaoPesagem) {
		return situacaoPesagem.getId().equals(this.getValue()) ? true : false;
	}

}
