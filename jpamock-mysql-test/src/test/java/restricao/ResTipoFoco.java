package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = { "historicosEquipamentoInfracaoFoco" })
@ToString(exclude = { "historicosEquipamentoInfracaoFoco" })
@Table(name="RES_TBTIPO_FOCO")
public class ResTipoFoco {
	@Id
	@Column(name="TIPO_FOCO_ID", nullable = false)
	private Long id;
	private String descricao;

	@OneToMany(mappedBy = "tipoFoco")
	private List<ResHistoricoEquipamentoInfracaoFoco> historicosEquipamentoInfracaoFoco = new ArrayList<ResHistoricoEquipamentoInfracaoFoco>(0);
}
