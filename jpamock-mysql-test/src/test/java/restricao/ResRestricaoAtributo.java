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

@Entity
@Data
@EqualsAndHashCode(of={"programacao", "classificacao", "infracao", "equipamentoFaixa"})
@ToString(exclude = { "programacao", "equipamentoFaixa" })
@Table(name="RES_TBRESTRICAO_ATRIBUTO")
public class ResRestricaoAtributo {
	@Id
	@GeneratedValue
	@Column(name="RESTRICAO_ATRIBUTO_ID", nullable = false)
	private Long id;
	//@Pattern(regexp = "[a-nA-N]{1}[a-zA-Z]{2}[0-9]{4}", message = "A Placa deve estar no formato AAA1234. A Primeira letra deve estar no intervalo de A-N.")
	private String placa;
	private Double valorInicial;
	private Double valorFinal;

	@ManyToOne
	@JoinColumn(name = "programacao_id")
	private ResProgramacao programacao;

	@ManyToOne
	@JoinColumn(name = "classificacao_id")
	private ResClassificacao classificacao;

	@ManyToOne
	@JoinColumn(name = "veiculo_id")
	private ResVeiculo veiculo;

	@ManyToOne
	@JoinColumn(name = "equipamento_faixa_id")
	private ResEquipamentoFaixa equipamentoFaixa;

	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private ResPessoa proprietario;

	@ManyToOne
	@JoinColumn(name="infracao_id")
	private ResInfracao infracao;
}
