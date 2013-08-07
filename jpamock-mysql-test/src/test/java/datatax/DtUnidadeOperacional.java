package datatax;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity public class DtUnidadeOperacional {
	@Id @Column(name = "UNIDADE_OPERACIONAL_ID") private @Setter @Getter Long id;
	@Column(name = "DESCRICAO") private @Setter @Getter String descricao;

}
