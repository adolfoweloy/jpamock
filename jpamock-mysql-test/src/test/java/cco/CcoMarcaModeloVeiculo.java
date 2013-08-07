package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoMarcaModeloVeiculo {

	@Id
	@Column(name="MARCA_MODELO_VEICULO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	public CcoMarcaModeloVeiculo() {}
	
	public CcoMarcaModeloVeiculo(Long id) {
		this.id = id;
	}
	
}
