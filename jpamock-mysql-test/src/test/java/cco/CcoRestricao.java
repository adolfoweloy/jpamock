package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoRestricao {
	
	@Id
	@Column(name="RESTRICAO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	public CcoRestricao() {}
	
	public CcoRestricao(Long id) {
		this.id = id;
	}
	
}
