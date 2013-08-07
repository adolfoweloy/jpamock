package cco;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
public class CcoEquipamento {

	@Id
	@Column(name="EQUIPAMENTO_ID")	
	private @Getter @Setter Long id;
	
	@Column(name="CODIGO")
	private @Getter @Setter String codigo;
	
	@ManyToOne
	@JoinColumn(name="TIPO_EQUIPAMENTO_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter CcoTipoEquipamento tipoEquipamento;
	
	@ManyToOne
	@JoinColumn(name="UNIDADE_OPERACIONAL_ID")
	private @Getter @Setter CcoUnidadeOperacional unidadeOperacional;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="equipamento")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter List<CcoFaixa> listFaixa;
	
	public CcoEquipamento() {}
	
	public CcoEquipamento(Long id) {
		this.id = id;
	}
	
}