package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="RES_TBTIPO_VEICULO")
public class ResTipoVeiculo {

	@Id
	@Column(name="TIPO_VEICULO_ID", nullable=false)
	private @Getter @Setter Long id;

	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;

}
