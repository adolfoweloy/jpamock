package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@ToString(exclude = {"unidadeOperacional", "restricao"})
@EqualsAndHashCode(exclude = { "unidadeOperacional", "restricao" }, callSuper = false)
@Entity
@Table(name = "RES_TBIRREGULARIDADE")
public class ResIrregularidade {
	@Id
	@GeneratedValue
	@Column(name = "IRREGULARIDADE_ID", nullable = false)
	private Long id;
	private String descricao;

	@NotEmpty(message = "Código da Irregularidade não pode ser nulo.")
	@Column(name="codigo_irregularidade")
	private String codigoIrregularidade;

	@NotNull(message = "Unidade operacional não pode ser nula.")
	@ManyToOne
	@JoinColumn(name = "unidade_operacional_id")
	private ResUnidadeOperacional unidadeOperacional;

	@NotNull(message = "Restrição não pode ser nula.")
	@ManyToOne
	@JoinColumn(name = "restricao_id")
	private ResRestricao restricao;

	public ResIrregularidade() {}

	public ResIrregularidade(String codigoIrregularidade, ResUnidadeOperacional unidadeOperacional, ResRestricao restricao) {
		this.codigoIrregularidade = codigoIrregularidade;
		this.unidadeOperacional = unidadeOperacional;
		this.restricao = restricao;
	}
}
