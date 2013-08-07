package restricao;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="RES_TBPESSOA_JURIDICA")
public class ResPessoaJuridica extends ResPessoa {

	@NotEmpty(message = "O CNPJ deve ser informado.")
	private @Getter @Setter String cnpj;

	public ResPessoaJuridica() {
		setTipoPessoa(PESSOA_JURIDICA);
	}


}
