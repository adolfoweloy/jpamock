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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = { "faixa", "equipamento", "programacoes" })
@ToString(exclude = { "faixa", "equipamento", "programacoes" })
@Table(name = "RES_TBEQUIPAMENTO_FAIXA")
public class ResEquipamentoFaixa {
	@Id
	@GeneratedValue
	@Column(name = "EQUIPAMENTO_FAIXA_ID", nullable = false)
	private Long id;

	private Date dataInicioOperacao;
	private Date dataFimOperacao;

	@NotNull(message = "Faixa não pode ser nula.")
	@ManyToOne
	@JoinColumn(name = "faixa_id")
	private ResFaixa faixa;

	@NotNull(message = "Equipamento não pode ser nulo.")
	@ManyToOne
	@JoinColumn(name = "equipamento_id")
	private ResEquipamento equipamento;

	@ManyToMany
	@JoinTable(name = "RES_PROGRAMACAO_EQUIP_FAIXA", joinColumns = { @JoinColumn(name = "EQUIPAMENTO_FAIXA_ID") }, inverseJoinColumns = { @JoinColumn(name = "PROGRAMACAO_ID") })
	private List<ResProgramacao> programacoes = new ArrayList<ResProgramacao>(0);

	@Transient
	public String getDescricaoDetalhada(){
		StringBuilder desc = new StringBuilder();
		desc.append("(");
		desc.append(equipamento.getNome());
		desc.append(")");
		desc.append(" - ");
		desc.append(faixa.getDescricao());
		if(faixa.getSentido() != null){
			desc.append(" - Sentido ");
			desc.append(faixa.getSentido());
		}
		return desc.toString();
	}
}
