package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoTipoVeiculo {

	@Id
	@Column(name="TIPO_VEICULO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	public CcoTipoVeiculo(){}
	
	public CcoTipoVeiculo(Long id){
		this.id = id;
	}
	
}
