package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = { "historicosInfracoes" })
@ToString(exclude = { "historicosInfracoes" })
@Table(name="RES_TBGRAU_INFRACAO")
public class ResGrauInfracao {
	@Id
	@Column(name="GRAU_INFRACAO_ID", nullable = false)
	private Long id;
	private String descricao;
	@OneToMany(mappedBy = "grauInfracao")
	private List<ResHistoricoInfracao> historicosInfracoes = new ArrayList<ResHistoricoInfracao>(0);

	public ResGrauInfracao(){}
	public ResGrauInfracao(Long id){
		this.id = id;
	}
}
