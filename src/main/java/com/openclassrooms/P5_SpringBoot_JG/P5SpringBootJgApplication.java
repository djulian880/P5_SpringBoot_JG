package com.openclassrooms.P5_SpringBoot_JG;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import com.openclassrooms.P5_SpringBoot_JG.*;
import com.openclassrooms.P5_SpringBoot_JG.Util.ManageRepositoriesFromFile;
import com.openclassrooms.P5_SpringBoot_JG.controller.MedicalRecordController;
import com.openclassrooms.P5_SpringBoot_JG.controller.PersonController;
import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.P5_SpringBoot_JG.repository.FireStationRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.MedicalRecordRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.PersonRepository;

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
