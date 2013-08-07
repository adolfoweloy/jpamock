package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "RES_TBCOR_VEICULO")
public class ResCorVeiculo {
	@Id
	@Column(name = "COR_VEICULO_ID", nullable = false)
	private Long id;
	private String descricao;
}
