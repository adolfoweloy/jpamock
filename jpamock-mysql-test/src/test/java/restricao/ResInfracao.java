package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(exclude = { "tipoInfracao", "historicosInfracao",
		"restricoesAtributos", "historicosEquipamentoInfracaoFoco" }, callSuper = false)
@ToString
(exclude = { "tipoInfracao", "historicosInfracao",
		"restricoesAtributos", "historicosEquipamentoInfracaoFoco" })
@Entity
@Table(name = "RES_TBINFRACAO")
public class ResInfracao {
	@Id
	@GeneratedValue
	@Column(name = "INFRACAO_ID", nullable = false)
	private Long id;
	private String descricao;
	@Column(name = "CODIGO_CTB")
	private String codigoCTB;
	@ManyToOne
	@JoinColumn(name = "tipo_infracao_id")
	private ResTipoInfracao tipoInfracao;

	@OneToMany(mappedBy = "infracao")
	private List<ResHistoricoInfracao> historicosInfracao = new ArrayList<ResHistoricoInfracao>(
			0);

	@OneToMany(mappedBy = "infracao")
	private List<ResRestricaoAtributo> restricoesAtributos = new ArrayList<ResRestricaoAtributo>(
			0);

	@OneToMany(mappedBy = "infracao")
	private List<ResHistoricoEquipamentoInfracaoFoco> historicosEquipamentoInfracaoFoco = new ArrayList<ResHistoricoEquipamentoInfracaoFoco>(
			0);


}
