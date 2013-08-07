package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="RES_TBPESSOA_VEICULO")
@EqualsAndHashCode
public class ResPessoaVeiculo {

	@Id
	@GeneratedValue
	@Column(name="PESSOA_VEICULO_ID", nullable=false)
	private @Getter @Setter Long id;

	@NotNull(message="Pessoa não pode ser nulo.")
	@ManyToOne
	@JoinColumn(name="PESSOA_ID")
	private @Getter @Setter ResPessoa pessoa;

	@NotNull(message="Veículo não pode ser nulo.")
	@ManyToOne
	@JoinColumn(name="VEICULO_ID")
	private @Getter @Setter ResVeiculo veiculo;

}