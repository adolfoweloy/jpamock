package fiscal;


public enum FiscalTipoMercadoriaEnum {

	ACUCAR(1L), ADUBO(2L), ALGODAO(3L), MINERIO_DE_CRONITA(4L), QUEIJO(5L);

	FiscalTipoMercadoriaEnum(Long value) {
		this.value = value;
	}


	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}
	
	public boolean compareTo(FiscalTipoMercadoria tipoMercadoria) {
		return tipoMercadoria.getId().equals(this.getValue()) ? true : false;
	}

}