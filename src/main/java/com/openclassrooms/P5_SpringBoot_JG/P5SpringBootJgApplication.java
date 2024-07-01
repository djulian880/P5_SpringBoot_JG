package com.openclassrooms.P5_SpringBoot_JG;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class P5SpringBootJgApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(P5SpringBootJgApplication.class);
	
	
	@RequestMapping("/")
	String home() {
		return "Test connexion serveur";
	}
	
	


	public static void main(String[] args) {
		SpringApplication.run(P5SpringBootJgApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		
	
	}
	
	

}
