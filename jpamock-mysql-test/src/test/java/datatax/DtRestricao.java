package datatax;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity public class DtRestricao {

	@Id @GeneratedValue @Column(name = "RESTRICAO_ID") protected @Getter @Setter Integer id;
	@Column(name = "DESCRICAO") protected @Getter @Setter String descricao;

}
