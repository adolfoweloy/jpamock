package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="RES_TBHORA_AJUSTE_PESO")
public class ResAjusteHoraPeso {
	@Id
	@GeneratedValue
	@Column(name="HORA_AJUSTE_PESTO_ID", nullable = false)
	private Long id;
	@Column(name="PERCENTUAL_AJUSTE")
	private Double percentualAjuste;
	@Column(name="HORA_AJUSTE")
	private Integer horaAjuste;
	@ManyToOne
	@JoinColumn(name="CLASSIFICACAO_ID")
	private ResClassificacao classificacao;
	@ManyToOne
	@JoinColumn(name="EQUIPAMENTO_FAIXA_ID")
	private ResEquipamentoFaixa equipamentoFaixa;

}
