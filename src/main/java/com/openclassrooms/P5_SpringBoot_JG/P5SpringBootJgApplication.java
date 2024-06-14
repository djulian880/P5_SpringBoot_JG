package com.openclassrooms.P5_SpringBoot_JG;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class P5SpringBootJgApplication implements CommandLineRunner {
	
	@RequestMapping("/")
	String home() {
		return "Test connexion serveur";
	}
	
	


	public static void main(String[] args) {
		System.out.println("Test démarré");
		SpringApplication.run(P5SpringBootJgApplication.class, args);
		System.out.println("Test démarré2");
	}




	@Override
	public void run(String... args) throws Exception {
		System.out.println("Test démarré depuis commande run");
		
	}
	
	
	

}