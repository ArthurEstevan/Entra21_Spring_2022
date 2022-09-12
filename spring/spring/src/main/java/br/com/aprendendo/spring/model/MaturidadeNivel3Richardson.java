package br.com.aprendendo.spring.model;

import java.util.ArrayList;

// Essa classe serve para agregar a classe ItemNivel3 e também ser usada como super 
// classe em classes que serão utilizadas como respostas do servidor
public class MaturidadeNivel3Richardson {

	// =================== Atributos
	private ArrayList<ItemNivel3> links;

	// =================== Construtores
	MaturidadeNivel3Richardson() {
		super();
	}

	// =================== Getters e Setters
	MaturidadeNivel3Richardson(ArrayList<ItemNivel3> links) {
		super();
		this.links = links;
	}

	public ArrayList<ItemNivel3> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<ItemNivel3> links) {
		this.links = links;
	}

}
