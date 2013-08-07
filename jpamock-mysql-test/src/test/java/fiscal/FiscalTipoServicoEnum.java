package fiscal;


public enum FiscalTipoServicoEnum {

	ENVIO_DE_IMAGEM_PMV(1L);

	FiscalTipoServicoEnum(Long value) {
		this.value = value;
	}


	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}
	
	public boolean compareTo(FiscalTipoServico tipoServico) {
		return tipoServico.getId().equals(this.getValue()) ? true : false;
	}

}