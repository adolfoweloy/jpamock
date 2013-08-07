package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
public class CcoFaixa {

	@Id
	@Column(name="FAIXA_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	@Column(name="SENTIDO")
	private @Getter @Setter String sentido;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="EQUIPAMENTO_ID")
	@Cascade({CascadeType.PERSIST})
	private @Getter @Setter CcoEquipamento equipamento;
	
	public CcoFaixa(){}
	
	public CcoFaixa(Long id) {
		this.id = id;
	}
	
}
