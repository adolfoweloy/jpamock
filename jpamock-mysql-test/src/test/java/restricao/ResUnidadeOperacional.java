package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { "equipamentos" }, callSuper = false)
@ToString(exclude = {"equipamentos"})
@Entity
@Table(name="RES_TBUNIDADE_OPERACIONAL")
public class ResUnidadeOperacional {
	@Id
	@GeneratedValue
	@Column(name="UNIDADE_OPERACIONAL_ID", nullable = false)
	private Long id;
	private String descricao;
	@OneToMany(mappedBy = "unidadeOperacional")
	private List<ResEquipamento> equipamentos = new ArrayList<ResEquipamento>(0);

	public ResUnidadeOperacional() {}

	public ResUnidadeOperacional(Long id) {
		this.id = id;
	}

}
