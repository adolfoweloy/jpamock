package fiscal;

// Generated 10/07/2009 10:49:54 by Hibernate Tools 3.2.0.b9

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TBIMAGENS_TRAFEGO")
public class FiscalImagemTrafego implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "IMAGENS_TRAFEGO_ID", unique = true, nullable = false, precision = 38, scale = 0)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAFEGO_ID")
	private FiscalTrafego trafego;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIPO_IMAGEM_ID")
	private FiscalTipoImagem tipoImagem;

	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;

	@Column(name = "DIRETORIO_IMAGEM", nullable = false, length = 100)
	private String diretorioImagem;

	@Transient
	private byte[] imagemByteArray;

	@Transient
	private int sequenciaTransmissao;

	public FiscalImagemTrafego() {
	}

	public FiscalImagemTrafego(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FiscalTrafego getTrafego() {
		return trafego;
	}

	public void setTrafego(FiscalTrafego trafego) {
		this.trafego = trafego;
	}

	public FiscalTipoImagem getTipoImagem() {
		return tipoImagem;
	}

	public void setTipoImagem(FiscalTipoImagem tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiretorioImagem() {
		return diretorioImagem;
	}

	public void setDiretorioImagem(String diretorioImagem) {
		this.diretorioImagem = diretorioImagem;
	}

	public byte[] getImagemByteArray() {
		return imagemByteArray;
	}

	public void setImagemByteArray(byte[] imagemByteArray) {
		this.imagemByteArray = imagemByteArray;
	}

	public int getSequenciaTransmissao() {
		return sequenciaTransmissao;
	}

	public void setSequenciaTransmissao(int sequenciaTransmissao) {
		this.sequenciaTransmissao = sequenciaTransmissao;
	}

}