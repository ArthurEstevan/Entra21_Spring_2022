package br.com.aprendendo.spring;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.aprendendo.spring.model.Pessoa;
import br.com.aprendendo.spring.model.Usuario;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static ArrayList<Pessoa> pessoas=new ArrayList<Pessoa>();

			// esse método serve para iniciliazar minha lista de pessoa acima
	@Override
	public void run(String... args) throws Exception {
		
		usuarios.add(new Usuario("arthur@email.com", "senha@forte"));
		usuarios.add(new Usuario("admin@email.com", "admin"));

		
		pessoas.add(new Pessoa("Pessoa1",33));
		pessoas.add(new Pessoa("Pessoa2",33));
		pessoas.add(new Pessoa("Pessoa3",33));
		pessoas.add(new Pessoa("Pessoa4",33));
		pessoas.add(new Pessoa("Pessoa5",33));
	}
	
	
	
	
	
	
	
	
	

}
