package restricao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(exclude = { "equipamentosFaixas", "unidadeOperacional", "tiposEquipamento" }, callSuper = false)
@ToString(exclude = { "equipamentosFaixas", "unidadeOperacional", "tiposEquipamento" })
@Entity
@Table(name="RES_TBEQUIPAMENTO")
public class ResEquipamento {
	@Id
	@GeneratedValue
	@Column(name="EQUIPAMENTO_ID", nullable = false)
	private Long id;

	@NotEmpty(message = "O equipamento deve ter um nome.")
	private String nome;
	private String fabricante;
	private String modelo;
	@OneToMany(mappedBy = "equipamento")
	private List<ResEquipamentoFaixa> equipamentosFaixas = new ArrayList<ResEquipamentoFaixa>(0);

	@NotNull(message = "O equipamento deve pertencer à uma unidade operacional.")
	@ManyToOne
	@JoinColumn(name = "unidade_operacional_id")
	private ResUnidadeOperacional unidadeOperacional;

	@NotNull(message = "O equipamento deve possuir um tipo de equipamento.")
	@ManyToMany
	@JoinTable(name="RES_EQUIP_TIPOEQUIP", joinColumns={@JoinColumn(name="EQUIPAMENTO_ID")}, inverseJoinColumns={@JoinColumn(name="TIPO_EQUIPAMENTO_ID")})
	private Set<ResTipoEquipamento> tiposEquipamento = new HashSet<ResTipoEquipamento>(0);

	@OneToMany(mappedBy = "equipamento")
	private List<ResHistoricoEquipamento> historicosEquipamento = new ArrayList<ResHistoricoEquipamento>(0);

	public ResEquipamento() {}
	public ResEquipamento(String nome, ResUnidadeOperacional unidadeOperacional) {
		this.nome = nome;
		this.unidadeOperacional = unidadeOperacional;
	}
}
