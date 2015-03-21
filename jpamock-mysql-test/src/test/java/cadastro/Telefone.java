package cadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "TELEFONE") public class Telefone {

	@Id @GeneratedValue @Column(name = "ID") private Long id;

	@Column(name = "CODIGO_AREA", nullable = false) private Integer codigoArea;

	@Column(name = "TELEFONE", nullable = false) private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}