package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoEspecieVeiculo {

	@Id
	@Column(name="ESPECIE_VEICULO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	public CcoEspecieVeiculo() {}
	
	public CcoEspecieVeiculo(Long id) {
		this.id = id;
	}
	
}
