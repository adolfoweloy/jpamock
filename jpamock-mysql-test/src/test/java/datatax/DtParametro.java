package datatax;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity public class DtParametro {

	@Id @Column(name = "PARAMETRO_ID") private @Getter @Setter Integer id;
	@Column(name = "TEMPO_REABERTURA_EM_SEGUNDOS") private @Getter @Setter Integer tempoReaberturaEmSegundos;
	@Column(name = "PERCENTUAL_FUGA") private @Setter @Getter Double percentualFuga;
	@Column(name = "TEMPO_TICKET_ABERTO_SEGUNDOS") private @Setter @Getter Integer tempoTicketAbertoEmSegundos;
	@Column(name = "PERCENTUAL_PESO_LIQUIDO") private @Setter @Getter Double percentualPesoLiquido;
	@Column(name = "UNIDADE_OPERACIONAL_ID") private @Setter @Getter Long idUnidadeOperacional;

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtParametro other = (DtParametro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
