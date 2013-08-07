package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoUsuario {

	@Id
	@Column(name="USUARIO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="NOME")
	private @Getter @Setter String nome;
	
	@ManyToOne
	@JoinColumn(name="UNIDADE_OPERACIONAL_ID")
	private @Getter @Setter CcoUnidadeOperacional unidadeOperacional;
	
}
