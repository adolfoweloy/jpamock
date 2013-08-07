package fiscal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBFLAG_PESAGEM")
public class FiscalFlagPesagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "FLAG_PESAGEM_ID", unique = true, nullable = false, precision = 38, scale = 0)
	private Long id;

	@Column(name = "NOME", nullable = false, length = 80)
	private String nome;

	public FiscalFlagPesagem() {
	}

	public FiscalFlagPesagem(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}