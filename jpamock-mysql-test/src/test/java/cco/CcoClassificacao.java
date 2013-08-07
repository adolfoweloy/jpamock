package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CcoClassificacao {

	@Id
	@Column(name="CLASSIFICACAO_ID")
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	@Column(name="IMAGEM_PATH")
	private @Getter @Setter String imagemPath;
	
	public CcoClassificacao(){}
	
	public CcoClassificacao(Long id){
		this.id = id;
	}
	
}