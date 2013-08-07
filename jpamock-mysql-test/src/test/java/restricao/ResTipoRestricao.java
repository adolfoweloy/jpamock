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

@Entity
@Data
@EqualsAndHashCode(exclude = { "restricoes" })
@ToString(exclude = { "restricoes" })
@Table(name="RES_TBTIPO_RESTRICAO")
public class ResTipoRestricao {
	@Id
	@GeneratedValue
	@Column(name="TIPO_RESTRICAO_ID", nullable = false)
	private Long id;
	private String nome;
	private String descricao;
	@OneToMany(mappedBy = "tipoRestricao")
	private List<ResRestricao> restricoes = new ArrayList<ResRestricao>(0);

	public ResTipoRestricao(){}
	public ResTipoRestricao(Long id){
		this.id = id;
	}
}
