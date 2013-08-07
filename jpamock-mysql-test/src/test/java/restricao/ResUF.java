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
@EqualsAndHashCode(exclude = { "municipios" }, callSuper = false)
@ToString(exclude = { "municipios" })
@Entity
@Table(name = "RES_TBUF")
public class ResUF {
	@Id
	@GeneratedValue
	@Column(name = "UF_ID", nullable = false)
	private Long id;
	@Column(name = "SIGLA_UF")
	private String siglaUF;
	@OneToMany(mappedBy = "uf")
	private List<ResMunicipio> municipios = new ArrayList<ResMunicipio>(0);
}
