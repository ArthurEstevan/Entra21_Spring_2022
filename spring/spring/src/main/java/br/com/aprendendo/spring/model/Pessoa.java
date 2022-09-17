package br.com.aprendendo.spring.model;

public class Pessoa extends Usuario {

	// =================== Atributos
	private String nome;
	private int idade;
	
	// =================== Construtores
	Pessoa() {
		super();
	}

	public Pessoa(String nome, int idade) {
		super();
		this.nome = nome;
		this.idade = idade;
	}

	// =================== Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
