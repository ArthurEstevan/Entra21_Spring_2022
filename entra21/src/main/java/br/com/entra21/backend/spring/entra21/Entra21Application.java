package br.com.entra21.backend.spring.entra21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class Entra21Application implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbc;
	
	public static void main(String[] args) {
		SpringApplication.run(Entra21Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "INSERT INTO programador (nome, qtd_linguagem) VALUES (?, ?)"; 
		int result = jdbc.update(sql, "Rubem", 10);
		
		String personagem = 
				"INSERT INTO personagem (habilidade, nome, acessorio, idade) VALUES (?, ?, ?, ?)";
		int insert = jdbc.update(personagem, "Vingança", "Motoqueiro-Fantasma", true, 1000);
	}
}
