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
public class CcoVeiculo {

	@Id
	@Column(name="VEICULO_ID")
	private @Getter @Setter Long id;
	@Column(name="PLACA")
	private @Getter @Setter String placa;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="COR_VEICULO_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter CcoCorVeiculo corVeiculo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TIPO_VEICULO_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter CcoTipoVeiculo tipoVeiculo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ESPECIE_VEICULO_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter CcoEspecieVeiculo especieVeiculo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MARCA_MODELO_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter CcoMarcaModeloVeiculo marcaModeloVeiculo;
	
	public CcoVeiculo(){}
	
	public CcoVeiculo(Long id){
		this.id = id;
	}
		
}