package datatax;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity public class DtPlaca {

	@Id @Column(name = "PLACA_ID") protected @Setter @Getter Long id;
	@Column(name = "SITUACAO") protected @Setter @Getter Long situacao;
	@Column(name = "PLACA") protected @Setter @Getter String placa;
	@Column(name = "UNIDADE_OPERACIONAL_ID") protected @Setter @Getter Long unidadeOperacional;

}
