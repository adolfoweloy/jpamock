package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { "historicoEquipamento", "tipoFoco", "infracao" }, callSuper = false)
@ToString(exclude = { "historicoEquipamento", "tipoFoco", "infracao" })
@Entity
@Table(name="RES_TBHISTORICO_EQUIP_INFR_FOCO")
public class ResHistoricoEquipamentoInfracaoFoco {
	@Id
	@GeneratedValue
	@Column(name="HISTORICO_EQUIP_INFR_FOCO_ID", nullable = false)
	private Long id;

	@Column(name="FOCO_PRINCIPAL")
	private Boolean focoPrincipal = false;

	@ManyToOne
	@JoinColumn(name = "historico_equipamento_id")
	private ResHistoricoEquipamento historicoEquipamento;

	@ManyToOne
	@JoinColumn(name = "tipo_foco_id")
	private ResTipoFoco tipoFoco;

	@ManyToOne
	@JoinColumn(name = "infracao_id")
	private ResInfracao infracao;

	private int quantidade;
}
