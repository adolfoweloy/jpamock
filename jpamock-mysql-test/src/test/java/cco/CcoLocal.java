package cco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
public class CcoLocal {

	@Id
	@Column(name="LOCAL_ID")
	@GeneratedValue
	private @Getter @Setter Long id;
	
	@Column(name="DESCRICAO")
	private @Getter @Setter String descricao;
	
	@Column(name="LATITUDE")
	private @Getter @Setter Double latitude;
	
	@Column(name="LONGITUDE")
	private @Getter @Setter Double longitude;
	
	@ManyToOne
	@JoinColumn(name="MUNICIPIO_ID")
	@Cascade({CascadeType.SAVE_UPDATE})
	private @Getter @Setter CcoMunicipio municipio;	
	
	public CcoLocal() {}
	
	public CcoLocal(CcoMunicipio municipio, String descrricao, Double latitude, Double longitude) {
		this.municipio = municipio;
		this.descricao = descrricao;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Transient
	public String getDescricaoDetalhe() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(descricao);
		if(municipio != null) {
			buffer.append(" - ");
			buffer.append(municipio.getDescricao());
			if(municipio.getUf() != null) {
				buffer.append("/");
				buffer.append(municipio.getUf().getSigla());
			}
		}
		return buffer.toString();
	}
	
}