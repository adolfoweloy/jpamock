package cco;

import lombok.Getter;

public enum CcoTipoFiltroPlaca {

	TIPO_PLACA_COMPLETA_INCOMPLETA(1L), TIPO_PLACA_COMPLETA(2L), TIPO_PLACA_INCOMPLETA(3L);
	
	private @Getter Long value;

	private CcoTipoFiltroPlaca(Long value) {
		this.value = value;
	}	
	
	
}