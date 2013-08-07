package restricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = { "historicosEquipamento" })
@ToString(exclude = { "historicosEquipamento" })
@Table(name="RES_TBTIPO_CRIPTOGRAFIA")
public class ResTipoCriptografia {
	@Id
	@Column(name="TIPO_CRIPTOGRAFIA_ID", nullable = false)
	private Long id;
	private String descricao;
	@Column(name="chave_privada")
	private String chavePrivada;
	@Column(name="chave_publica")
	private String chavePublica;
	@OneToMany(mappedBy = "tipoCriptografia")
	private List<ResHistoricoEquipamento> historicosEquipamento = new ArrayList<ResHistoricoEquipamento>(0);

	public ResTipoCriptografia(){}
	public ResTipoCriptografia(Long id){
		this.id = id;
	}
}
