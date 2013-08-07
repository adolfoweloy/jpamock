package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;

public class TestSingle extends TestCase {

	static JPAMock jpaMock;

	static {
		jpaMock = new JPAMock(Persistence.createEntityManagerFactory("jpamock_mysql"));
		jpaMock.clearAll();
	}

	public void test() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		Telefone t1 = new Telefone();
		t1.setCodigoArea(61);
		t1.setTelefone("30312569");
		Telefone t2 = new Telefone();
		t2.setCodigoArea(61);
		t2.setTelefone("91256987");
		telefones.add(t1);
		telefones.add(t2);
		jpaMock.when(Pessoa.class, "telefones");
		jpaMock.thenInject(telefones);
		jpaMock.mock(Pessoa.class);
	}

	@Entity @Table(name = "PESSOA") public static class Pessoa {

		@Id @GeneratedValue @Column(name = "ID") private Long id;

		@Column(name = "NOME", nullable = false) private String nome;

		@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL }) private List<Telefone> telefones;

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

		public List<Telefone> getTelefones() {
			return telefones;
		}

		public void setTelefones(List<Telefone> telefones) {
			this.telefones = telefones;
		}
	}

	@Entity @Table(name = "TELEFONE") public static class Telefone {

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

}
