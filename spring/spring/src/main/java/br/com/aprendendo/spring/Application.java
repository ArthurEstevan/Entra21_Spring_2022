package br.com.aprendendo.spring;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.aprendendo.spring.model.Pessoa;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static ArrayList<Pessoa> pessoas=new ArrayList();

			// esse método serve para iniciliazar minha lista de pessoa acima
	@Override
	public void run(String... args) throws Exception {
		pessoas.add(new Pessoa("Pessoa1",33));
		pessoas.add(new Pessoa("Pessoa2",33));
		pessoas.add(new Pessoa("Pessoa3",33));
		pessoas.add(new Pessoa("Pessoa4",33));
		pessoas.add(new Pessoa("Pessoa5",33));
	}
	
	
	
	
	
	
	
	
	

}
