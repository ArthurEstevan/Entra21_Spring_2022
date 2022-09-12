package br.com.aprendendo.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// declaramos que está classe é uma classe de controller

@RestController
@CrossOrigin(origins = "*") // ainda não sei PERGUNTAR TERÇA
public class SistemaController {

	// =================== escrevemos o tipo do controller e seu status de retorno
	 
	// TIPO
	
	// @GetMapping("/")
	// @GetMapping("/customizada/{mensagem}")
	
	// STATUS
	
	// @ResponseStatus(HttpStatus.OK)
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String listAll(Model model) {
		return "Estou ON";
	}
	
	@GetMapping("/customizada/{mensagem}")
	@ResponseStatus(HttpStatus.OK)
	public String customizada(@PathVariable("mensagem") String mensagem) {
	return "Sera que eu recebi certo? "+ mensagem;
	}
}
