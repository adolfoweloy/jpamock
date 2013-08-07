package restricao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { "municipio", "irregularidades", "classificacao", "marcaModelo", "cor" }, callSuper = false)
@ToString(exclude = { "municipio", "irregularidades", "classificacao", "marcaModelo", "cor" })
@Entity
@Table(name="RES_TBVEICULO", uniqueConstraints=@UniqueConstraint(columnNames="placa"))
public class ResVeiculo {
	@Id
	@GeneratedValue
	@Column(name="VEICULO_ID", nullable = false)
	private Long id;
	private Integer anoFabricacao;
	private Integer anoModelo;
	private String chassi;
	private Date dataConsulta;
	private String renavam;
	private Double tara;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio_id")
	private ResMunicipio municipio;
	@ManyToMany
	@JoinTable(name = "RES_IRREGULARIDADE_VEICULO", joinColumns = { @JoinColumn(name = "VEICULO_ID") }, inverseJoinColumns = { @JoinColumn(name = "IRREGULARIDADE_ID") })
	private List<ResIrregularidade> irregularidades = new ArrayList<ResIrregularidade>();
	//@Pattern(regexp = "[a-pA-P]{1}[a-zA-Z]{2}[0-9]{4}", message = "A Placa deve estar no formato AAA1234. A Primeira letra deve estar no intervalo de A-P.")
	private String placa;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLASSIFICACAO_ID")
	private ResClassificacao classificacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COR_VEICULO_ID")
	private ResCorVeiculo cor;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MARCA_MODELO_ID")
	private ResMarcaModelo marcaModelo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TIPO_VEICULO_ID")
	private ResTipoVeiculo tipoVeiculo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ESPECIE_VEICULO_ID")
	private ResEspecieVeiculo especieVeiculo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORIA_VEICULO_ID")
	private ResCategoriaVeiculo categoriaVeiculo;


	@Transient
	public boolean isPersisted(){
		return this.id != null;
	}
}