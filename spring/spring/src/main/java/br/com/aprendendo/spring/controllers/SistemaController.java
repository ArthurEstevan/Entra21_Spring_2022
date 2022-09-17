package br.com.aprendendo.spring.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aprendendo.spring.Application;
import br.com.aprendendo.spring.exceptions.DuplicateRequestException;
import br.com.aprendendo.spring.model.ItemNivel3;
import br.com.aprendendo.spring.model.Usuario;

// declaramos que está classe é uma classe de controller

@RestController
@CrossOrigin(origins = "*") // ainda não sei PERGUNTAR TERÇA
@RequestMapping("/sistema")
public class SistemaController {

	// =================== escrevemos o tipo do controller e seu status de retorno

	// TIPO

	// @GetMapping("/")
	// @GetMapping("/customizada/{mensagem}")

	// STATUS

	// @ResponseStatus(HttpStatus.OK)

	private final String PATH = "http://localhost:8080/sistema";

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String listAll(Model model) {
		return "Estou ON";
	}

	@GetMapping("/customizada/{mensagem}")
	@ResponseStatus(HttpStatus.OK)
	public String customizada(@PathVariable("mensagem") String mensagem) {
		return "Sera que eu recebi certo? " + mensagem;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Usuario> login(@RequestBody Usuario credenciais) {

		List<Usuario> response = Application.usuarios.stream()
				.filter(usuario -> usuario.getEmail().equals(credenciais.getEmail())
						&& usuario.getSenha().equals(credenciais.getSenha()))
				.toList();
		response.forEach(usuario -> {
			setMaturidadeNivel3(usuario);
		});
		return response;
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Usuario register(@RequestBody Usuario credenciais) {

		List<Usuario> busca = Application.usuarios.stream()
				.filter(usuario -> usuario.getEmail().equals(credenciais.getEmail())).toList();
		if (!busca.isEmpty()) {
			throw new DuplicateRequestException();
		} else {
			Application.usuarios.add(credenciais);
		}
		setMaturidadeNivel3(credenciais);
		return credenciais;
	}

	private void setMaturidadeNivel3(Usuario usuario) {
		ArrayList<String> headers = new ArrayList(
				Arrays.asList("Accept : application/json", "Content-type : application/json"));

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);

		try {

			usuario.setLinks(null);

			String json = mapper.writeValueAsString(usuario);

			usuario.setLinks(new ArrayList<>());
			usuario.getLinks().add(new ItemNivel3("GET", PATH, null, null));
			usuario.getLinks().add(new ItemNivel3("GET", PATH + "/" + usuario.getEmail(), null, null));

			usuario.getLinks().add(new ItemNivel3("POST", PATH, headers, json));
			usuario.getLinks().add(new ItemNivel3("POST", PATH + "/register", headers, json));

			usuario.getLinks().add(new ItemNivel3("PUT", PATH + "/" + usuario.getSenha(), headers, json));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
