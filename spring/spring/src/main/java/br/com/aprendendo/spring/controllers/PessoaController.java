package br.com.aprendendo.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aprendendo.spring.Application;
import br.com.aprendendo.spring.model.ItemNivel3;
import br.com.aprendendo.spring.model.Pessoa;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pessoa")
public class PessoaController {

	private final String PATH = "http://localhost:8080/pessoas";

	// Get para obter uma lista de pessoas
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Pessoa> listAll() {
		return obterListaCompleta();
	}

	// Get para obter uma pessoa
	@GetMapping("/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Pessoa> get(@PathVariable("nome") String nome) {

		List<Pessoa> response = Application.pessoas.stream().filter(pessoa -> nome.equals(pessoa.getNome())).toList();
		response.forEach(pessoa -> {
			setMaturidadeNivel3(pessoa);
		});

		return response;
	}

	// Post para criar uma pessoa
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody List<Pessoa> create(@RequestBody Pessoa pessoaNova) {

		Application.pessoas.add(pessoaNova);
		return obterListaCompleta();
	}

	// Put para atualizar uma pessoa
	@PutMapping("/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Pessoa> update(@PathVariable("nome") String nome, @RequestBody Pessoa pessoaEditada) {

		Application.pessoas.stream().filter(busca -> nome.equals(busca.getNome())).forEach(item -> {
			item.setNome(pessoaEditada.getNome());
			item.setIdade(pessoaEditada.getIdade());
		});

		return obterListaCompleta();
	}

	// Delete para remover uma pessoa
	@DeleteMapping("/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Pessoa> delete(@PathVariable("nome") String nome) {

		Application.pessoas.removeIf(busca -> nome.equals(busca.getNome()));

		return obterListaCompleta();
	}

	// Essa ação é o deta\lhe que define as informações do nivel 3 para cada item da
	// resposta
	// Essa ação é o detalhe que define as informações do nivel 3 para cada item da
	// resposta
	private void setMaturidadeNivel3(Pessoa pessoa) {

		ArrayList<String> headers = new ArrayList();

		headers.add("Accept : application/json");
		headers.add("Content-type : application/json");

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);

		try {

			pessoa.setLinks(null);

			String json = mapper.writeValueAsString(pessoa);

			pessoa.setLinks(new ArrayList<>());
			pessoa.getLinks().add(new ItemNivel3("GET", PATH, null, null));
			pessoa.getLinks().add(new ItemNivel3("GET", PATH + "/" + pessoa.getNome(), null, null));
			pessoa.getLinks().add(new ItemNivel3("POST", PATH, headers, json));
			pessoa.getLinks().add(new ItemNivel3("PUT", PATH + "/" + pessoa.getNome(), headers, json));

		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}

	// Essa ação estava se repetindo bastante então foi necessário criar um método
	// com
	// conceito de procedimento para realizar essa ação repetitiva que se resume em
	// obter
	// uma cópia da lista e adicionar os links para alcançar o nivel de maturidade 3
	private List<Pessoa> obterListaCompleta() {

		List<Pessoa> response = Application.pessoas.stream().toList();
		response.forEach(pessoa -> {
			setMaturidadeNivel3(pessoa);
		});
		return response;
	}

}
