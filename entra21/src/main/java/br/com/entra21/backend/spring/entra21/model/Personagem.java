package br.com.entra21.backend.spring.entra21.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personagem")
public class Personagem extends MaturidadeNivel3Richardson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String habilidade;
	private String nome;
	private Boolean acessorio;
	private Integer idade;
	
	Personagem() {
		super();
	}

	Personagem(Integer id, String habilidade, String nome, Boolean acessorio, Integer idade) {
		super();
		this.id = id;
		this.habilidade = habilidade;
		this.nome = nome;
		this.acessorio = acessorio;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Boolean acessorio) {
		this.acessorio = acessorio;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
}
