package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "RES_TBPESSOA")
@EqualsAndHashCode(exclude={"veiculos"})
public class ResPessoa {

	public static final Integer PESSOA_JURIDICA = 1;
	public static final Integer PESSOA_FISICA = 2;

	@Id
	@GeneratedValue
	@Column(name="PESSOA_ID", nullable=false)
	private @Getter @Setter Long id;

	@Column(name="TIPO_PESSOA")
	private @Getter @Setter Integer tipoPessoa;

	@NotEmpty(message = "O nome/razão social deve ser informado.")
	private @Getter @Setter String nome;

	@OneToMany
	private @Getter @Setter List<ResPessoaVeiculo> veiculos = new ArrayList<ResPessoaVeiculo>(0);

}