package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="RES_TBTIPO_EQUIPAMENTO")
public class ResTipoEquipamento {
	@Id
	@Column(name="TIPO_EQUIPAMENTO_ID", nullable = false)
	private Long id;
	private String descricao;

	public ResTipoEquipamento(){}
	public ResTipoEquipamento(Long id){
		this.id = id;
	}
}
