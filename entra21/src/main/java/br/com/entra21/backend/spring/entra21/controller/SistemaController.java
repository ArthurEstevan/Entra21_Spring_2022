package br.com.entra21.backend.spring.entra21.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sistema")
public class SistemaController {

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public String on() {
		return "Estou ON";
	}
}
