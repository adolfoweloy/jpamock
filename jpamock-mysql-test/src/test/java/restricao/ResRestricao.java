package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(exclude = { "programacoes", "irregularidades" }, callSuper = false)
@ToString(exclude = { "programacoes", "irregularidades" })
@Entity
@Table(name="RES_TBRESTRICAO")
public class ResRestricao {
	@Id
	@NotNull(message = "Deve ser selecionado uma restrição existente.")
	@Column(name="RESTRICAO_ID", nullable = false)
	private Long id;
	@NotEmpty(message = "A Restrição deve possuir uma descrição.")
	@Column(name="DESCRICAO", updatable=false)
	private String descricao;
	private boolean fixo;
	@OneToMany
	private List<ResProgramacao> programacoes = new ArrayList<ResProgramacao>(0);
	@OneToMany(mappedBy="restricao")
	private List<ResIrregularidade> irregularidades;
	@ManyToOne
	@JoinColumn(name = "tipo_restricao_id")
	private ResTipoRestricao tipoRestricao;

	public ResRestricao() {}

	public ResRestricao(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
}
