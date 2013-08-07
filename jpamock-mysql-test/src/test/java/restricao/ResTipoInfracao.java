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
@EqualsAndHashCode(of={"id"})
@ToString(exclude = { "infracoes" })
@Table(name="RES_TBTIPO_INFRACAO")
public class ResTipoInfracao {
	@Id
	@Column(name="TIPO_INFRACAO_ID", nullable = false)
	private Long id;
	private String descricao;
	@OneToMany(mappedBy = "tipoInfracao")
	private List<ResInfracao> infracoes = new ArrayList<ResInfracao>(0);

	public ResTipoInfracao(){}
	public ResTipoInfracao(Long id){
		this.id = id;
	}
}
