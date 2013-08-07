package restricao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(exclude = { "restricao", "dataInicio", "dataFim", "texto", "restricoesAtributos", "equipamentosFaixas" })
@ToString(exclude = { "restricao", "dataInicio", "dataFim", "texto", "restricoesAtributos", "equipamentosFaixas" })
@Entity
@Table(name = "RES_TBPROGRAMACAO")
public class ResProgramacao {
	@Id
	@GeneratedValue
	@Column(name = "PROGRAMACAO_ID", nullable = false)
	private Long id;
	@NotNull(message = "A Data Fim da programação não pode ser nula.")
	private Date dataFim;
	@NotNull(message = "A Data Início da programação não pode ser nula.")
	private Date dataInicio;
	@NotEmpty(message = "A programação deve possuir uma descrição.")
	@Column(unique = true)
	private String descricao;
	private String texto;
	private Long usuario_id;

	@NotNull(message = "A programação deve pertencer à uma restrição.")
	@ManyToOne
	@JoinColumn(name = "restricao_id")
	private ResRestricao restricao;

	@OneToMany
	private List<ResRestricaoAtributo> restricoesAtributos = new ArrayList<ResRestricaoAtributo>(0);

	@NotNull(message = "A programação deve ter pelo menos um EquipamentoFaixa.")
	@Size(min = 1, message = "A programação deve ter pelo menos um EquipamentoFaixa.")
	@ManyToMany
	@JoinTable(name = "RES_PROGRAMACAO_EQUIP_FAIXA", joinColumns = { @JoinColumn(name = "PROGRAMACAO_ID") }, inverseJoinColumns = { @JoinColumn(name = "EQUIPAMENTO_FAIXA_ID") })
	private List<ResEquipamentoFaixa> equipamentosFaixas = new ArrayList<ResEquipamentoFaixa>(0);

	public ResProgramacao() {
	}

	public ResProgramacao(Date dataInicio, Date dataFim, String descricao, ResRestricao restricao) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
		this.restricao = restricao;
	}

	@Transient
	public boolean isPersisted() {
		return this.id != null;
	}
}
