package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoUF {

	@Id
	@Column(name="UF_ID")
	private @Getter @Setter Long id;
	
	@Column(name="SIGLA_UF")
	private @Getter @Setter String sigla;
	
	public CcoUF() {}
	
	public CcoUF(Long id) {
		this.id = id;
	}
	
}
