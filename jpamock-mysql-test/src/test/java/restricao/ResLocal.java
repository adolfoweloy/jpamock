package restricao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(exclude = { "municipio" }, callSuper = false)
@ToString(exclude = { "municipio" })
@Entity
@Table(name="RES_TBLOCAL")
public class ResLocal {
	@Id
	@GeneratedValue
	@Column(name="LOCAL_ID", nullable = false)
	private Long id;
	private Integer km;
	@NotEmpty(message = "Local não pode estar vazio.")
	private String local;
	private String logradouro;
	private Integer metro;
	private String rodovia;
	private Double latitude;
	private Double longitude;
	@NotNull(message = "Local deve pertencer à um município.")
	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private ResMunicipio municipio;

	public ResLocal() {}
	public ResLocal(String local, ResMunicipio municipio) {
		this.local = local;
		this.municipio = municipio;
	}
}
