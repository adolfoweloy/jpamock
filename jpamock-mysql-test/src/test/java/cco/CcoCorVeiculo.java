package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoCorVeiculo {

	@Id
	@Column(name="COR_VEICULO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO", unique=true)
	private @Getter @Setter String descricao;
	
	public CcoCorVeiculo(){}
	
	public CcoCorVeiculo(Long id){
		this.id = id;
	}
	
}
