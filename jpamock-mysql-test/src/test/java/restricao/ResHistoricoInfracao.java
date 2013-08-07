package restricao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { "grauInfracao", "infracao" }, callSuper = false)
@ToString(exclude = { "grauInfracao", "infracao" })
@Entity
@Table(name="RES_TBHISTORICO_INFRACAO")
public class ResHistoricoInfracao {
	@Id
	@GeneratedValue
	@Column(name="HISTORICO_INFRACAO", nullable = false)
	private Long id;

	@Column(name="data_inicial")
	private Date dataInicial;
	@Column(name="data_final")
	private Date dataFinal;
	private String artigo;
	private String pontuacao;

	@ManyToOne
	@JoinColumn(name = "grau_infracao_id")
	private ResGrauInfracao grauInfracao;

	@NotNull(message = "O histórico de infracao deve possuir uma infracao.")
	@ManyToOne
	@JoinColumn(name = "infracao_id")
	private ResInfracao infracao;
}
