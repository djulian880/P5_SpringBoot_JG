package com.openclassrooms.P5_SpringBoot_JG;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import com.openclassrooms.P5_SpringBoot_JG.*;
import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.P5_SpringBoot_JG.repository.FirestationRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.MedicalRecordRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.PersonRepository;
import com.openclassrooms.P5_SpringBoot_JG.service.ManageRepositoriesFromFile;

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
		
		System.out.println("Test lecture firestations");
		
		
		
		String path="src/main/resources/data.json";
		
		FirestationRepository frepo=new FirestationRepository();
		MedicalRecordRepository medicalRecordRepo=new MedicalRecordRepository();
		PersonRepository personRepo=new PersonRepository();
		ManageRepositoriesFromFile m= new ManageRepositoriesFromFile();
	//	m.readFireStations(path,frepo);
		
		
		String contenuFichier=ManageRepositoriesFromFile.returnContentOfFileAsString(path);
		String contenuNoeud=ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(contenuFichier,"firestations");
		m.readFireStations(contenuNoeud,frepo);
		
		contenuNoeud=ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(contenuFichier,"medicalrecords");
		m.readMedicalRecords(contenuNoeud,medicalRecordRepo);
		
		contenuNoeud=ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(contenuFichier,"persons");
		m.readPersons(contenuNoeud,personRepo);
		
		
		
		
		
		System.out.println(contenuNoeud);
		//frepo.firestations=ManageRepositoriesFromFile.readCollectionFromJSONString(contenuNoeud,new FireStation());
		

		System.out.println("firestations:");
		for(int i=0;i<frepo.firestations.size();i++) {
			System.out.println(frepo.firestations.get(i).address);
		}
		System.out.println("medicalrecords:");
		for(int i=0;i<medicalRecordRepo.medicalRecords.size();i++) {
			System.out.println(medicalRecordRepo.medicalRecords.get(i).medications);
		}
		System.out.println("persons:");
		for(int i=0;i<personRepo.persons.size();i++) {
			System.out.println(personRepo.persons.get(i).firstName);
		}
		
		
		System.out.println("Fin lecture firestations");
	}
	
	
	

}
