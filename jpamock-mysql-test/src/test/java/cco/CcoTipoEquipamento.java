package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoTipoEquipamento {

	@Id
	@Column(name="TIPO_EQUIPAMENTO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	public CcoTipoEquipamento() {}
	
	public CcoTipoEquipamento(Long id) {
		this.id = id;		
	}
	
	CcoTipoEquipamento(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
}
