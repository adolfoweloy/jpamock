package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { "uf", "locais" }, callSuper = false)
@ToString(exclude = { "uf", "locais" })
@Entity
@Table(name="RES_TBMUNICIPIO")
public class ResMunicipio {
	@Id
	@GeneratedValue
	@Column(name="MUNICIPIO_ID", nullable = false)
	private Long id;
	@Column(name = "descricao")
	private String nome;
	@ManyToOne
	@JoinColumn(name = "uf_id")
	private ResUF uf;
	@OneToMany(mappedBy = "municipio")
	private List<ResLocal> locais = new ArrayList<ResLocal>(0);
}
