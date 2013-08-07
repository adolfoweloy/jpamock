package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoUnidadeOperacional {

	@Id
	@Column(name="UNIDADE_OPERACIONAL_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UNIDADE_AGRUPADORA_ID")
	private @Getter @Setter CcoUnidadeOperacional unidadeAgrupadora;
	
}
