package br.com.entra21.backend.spring.entra21.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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

import org.springframework.http.HttpStatus;

import br.com.entra21.backend.spring.entra21.model.ItemNivel3;
import br.com.entra21.backend.spring.entra21.model.Personagem;
import br.com.entra21.backend.spring.entra21.repository.IPersonagemRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/personagem")
public class PersonagemController {

	private final String PATH = "localhost:8080/personagem";

	@Autowired
	private IPersonagemRepository personagemRepository;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<Personagem> listAll() {
		List<Personagem> response = personagemRepository.findAll();
		response.forEach(personagem -> {
			setMaturidadeNivel3(personagem);
		});
		return response;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Personagem> get(@PathVariable("id") int id) {
		List<Personagem> response = personagemRepository.findById(id).stream().toList();
		response.forEach(personagem -> {
			setMaturidadeNivel3(personagem);
		});
		return response;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Personagem create(@RequestBody Personagem novoProgramador) {
		Personagem response = personagemRepository.save(novoProgramador);
		return response;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Personagem update(@PathVariable("id") int id, @RequestBody Personagem personagemEditado) {
		Personagem atual = personagemRepository.findById(id).get();

		atual.setNome(personagemEditado.getNome());
		atual.setHabilidade(personagemEditado.getHabilidade());
		atual.setIdade(personagemEditado.getIdade());
		atual.setAcessorio(personagemEditado.getAcessorio());
		atual = personagemRepository.save(atual);
		setMaturidadeNivel3(atual);
		return atual;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean delete(@PathVariable("id") int id) {
		personagemRepository.deleteById(id);
		return !personagemRepository.existsById(id);
	}

	private void setMaturidadeNivel3(Personagem personagem) {

		final String PATH = "localhost:8080/personagens";

		ArrayList<String> headers = new ArrayList<String>();

		headers.add("Accept : application/json");

		headers.add("Content-type : application/json");

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);

		try {

			Personagem clone = mapper.readValue(mapper.writeValueAsString(personagem), Personagem.class);

			clone.setLinks(null);

			String nomeAtual = clone.getNome();

			clone.setNome("Nome diferente");

			String jsonUpdate = mapper.writeValueAsString(clone);

			clone.setNome(nomeAtual);

			clone.setId(null);

			String jsonCreate = mapper.writeValueAsString(clone);

			personagem.setLinks(new ArrayList<>());

			personagem.getLinks().add(new ItemNivel3("GET", PATH, null, null));

			personagem.getLinks().add(new ItemNivel3("GET", PATH + "/" + personagem.getId(), null, null));

			personagem.getLinks().add(new ItemNivel3("POST", PATH, headers, jsonCreate));

			personagem.getLinks().add(new ItemNivel3("PUT", PATH + "/" + personagem.getId(), headers, jsonUpdate));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
