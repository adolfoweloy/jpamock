package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
public class CcoMunicipio {

	@Id
	@Column(name="MUNICIPIO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	@ManyToOne
	@JoinColumn(name="UF_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter CcoUF uf;
	
	public CcoMunicipio() {}
	
	public CcoMunicipio(Long id) {
		this.id = id;
	}
	
}
