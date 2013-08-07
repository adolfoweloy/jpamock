package restricao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { "equipamento", "tipoCriptografia", "historicosEquipamentoInfracaoFoco" }, callSuper = false)
@ToString(exclude = { "equipamento", "tipoCriptografia", "historicosEquipamentoInfracaoFoco" })
@Entity
@Table(name="RES_TBHISTORICO_EQUIPAMENTO")
public class ResHistoricoEquipamento {
	@Id
	@GeneratedValue
	@Column(name="HISTORICO_EQUIPAMENTO_ID", nullable = false)
	private Long id;

	@NotNull(message = "O histórico de equipamento deve possuir um equipamento.")
	@ManyToOne
	@JoinColumn(name = "equipamento_id")
	private ResEquipamento equipamento;

	@ManyToOne
	@JoinColumn(name = "tipo_criptografia_id")
	private ResTipoCriptografia tipoCriptografia;

	private Double latitude;
	private Double longitude;
	@Column(name="data_inicial")
	private Date dataInicial;
	@Column(name="data_final")
	private Date dataFinal;

	@OneToMany(mappedBy = "historicoEquipamento")
	private List<ResHistoricoEquipamentoInfracaoFoco> historicosEquipamentoInfracaoFoco = new ArrayList<ResHistoricoEquipamentoInfracaoFoco>(0);
}
