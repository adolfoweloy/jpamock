package datatax;

import lombok.Getter;

public enum DtSituacaoTicketEnum {
	LIVRE(1L), FISCALIZAR(2L), RETIDO(3L), LIBERADO(4L), AUTUADO(5L), PESAGEM(6L), FUGA_COMPROVADA(7L), FECHAMENTO_AUTOMATICO(8L), ATENDIMENTO(9L), CONFIRMAR_PESO(10L);

	private @Getter Long value;

	private DtSituacaoTicketEnum(Long value) {
		this.value = value;
	}

}
