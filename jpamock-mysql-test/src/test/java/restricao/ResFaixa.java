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
@EqualsAndHashCode(exclude = { "local", "equipamentosFaixas" }, callSuper = false)
@ToString(exclude = { "local", "equipamentosFaixas" })
@Entity
@Table(name="RES_TBFAIXA")
public class ResFaixa {
	@Id
	@GeneratedValue
	@Column(name="FAIXA_ID", nullable = false)
	private Long id;
	private Date dataAfericao;
	private Date dataVencimentoAfericao;
	@Column(name="NUMERO_LAUDO")
	private String numeroLaudo;
	private String sentido;
	private String descricao;
	@NotNull(message = "Nome não pode ser nulo.")
	private Integer nome;
	@NotNull(message = "Faixa deve pertencer à um local.")
	@ManyToOne
	@JoinColumn(name = "local_id")
	private ResLocal local;
	@OneToMany(mappedBy = "faixa")
	private List<ResEquipamentoFaixa> equipamentosFaixas = new ArrayList<ResEquipamentoFaixa>(0);

	public ResFaixa() {}
	public ResFaixa(Integer nome, ResLocal local) {
		this.nome = nome;
		this.local = local;
	}
}