package com.openclassrooms.P5_SpringBoot_JG;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;


@SpringBootApplication
public class P5SpringBootJgApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(P5SpringBootJgApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(P5SpringBootJgApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		
	
	}
	
	

}
