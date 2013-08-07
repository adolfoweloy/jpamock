package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="RES_TBMARCA_MODELO")
public class ResMarcaModelo {

	@Id
	@Column(name="MARCA_MODELO_ID", nullable=false)
	private Long id;
	private String descricao;
}