package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(of={"id"}, callSuper = false)
@ToString
@Entity
@Table(name="RES_TBCLASSIFICACAO")
public class ResClassificacao {
	@Id
	@GeneratedValue
	@Column(name="CLASSIFICACAO_ID", nullable = false)
	private Long id;

	@NotEmpty(message = "O Nome da classificação não pode ser nulo")
	private String nome;

	private String silhueta;

	private int numeroEixos;

	private Double pbt;

	@NotNull(message = "O tamanho fim da classificação não pode ser nulo")
	private Double tamanhoFim;

	@NotNull(message = "O tamanho inicio da classificação não pode ser nulo")
	private Double tamanhoInicio;

	private Double taraPadrao;

	public ResClassificacao() {}

	public ResClassificacao(String nome, Double tamanhoInicio, Double tamanhoFim) {
		setNome(nome);
		setTamanhoInicio(tamanhoInicio);
		setTamanhoFim(tamanhoFim);
	}
}
