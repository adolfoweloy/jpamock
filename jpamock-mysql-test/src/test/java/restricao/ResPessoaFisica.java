package restricao;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="RES_TBPESSOA_FISICA")
public class ResPessoaFisica extends ResPessoa {

	@NotEmpty(message = "O CPF deve ser informado.")
	private @Getter @Setter String cpf;

	public ResPessoaFisica() {
		setTipoPessoa(PESSOA_FISICA);
	}



}