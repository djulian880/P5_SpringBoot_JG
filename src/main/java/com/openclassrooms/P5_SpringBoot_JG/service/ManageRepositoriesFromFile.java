package com.openclassrooms.P5_SpringBoot_JG.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.repository.FirestationRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.MedicalRecordRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.PersonRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class ManageRepositoriesFromFile {

	public static String returnContentOfFileAsString(String filepath) {
		String content = null;
		Path filePath = Path.of(filepath);

		try {

			content = Files.readString(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	public static String returnContentOfJSONNodeAsString(String content, String nodeName) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		String contentOfNode = null;
		try {
			jsonNode = objectMapper.readTree(content);
			// System.out.println(nodeName);
			// System.out.println(jsonNode.get(nodeName).isContainerNode());
			/*
			 * Iterator<String> iterator = jsonNode.fieldNames();
			 * 
			 * while (iterator.hasNext()) { System.out.println("Element Value= " +
			 * iterator.next()); }
			 */

			contentOfNode = jsonNode.get(nodeName).toString();
			// System.out.println("Contenu du noeud");
			// System.out.println(contentOfNode);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contentOfNode;
	}

	public void readFireStations(String content, FirestationRepository firestationRepo) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			firestationRepo.firestations = objectMapper.readValue(content, new TypeReference<ArrayList<FireStation>>() {
			});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readMedicalRecords(String content, MedicalRecordRepository medicalRecordRepo) {

		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		objectMapper.setDateFormat(df);
		try {
			medicalRecordRepo.medicalRecords = objectMapper.readValue(content, new TypeReference<ArrayList<MedicalRecord>>() {
			});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readPersons(String content, PersonRepository personRepo) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			personRepo.persons = objectMapper.readValue(content, new TypeReference<ArrayList<Person>>() {
			});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// BUG A METTRE à jour
	public static <T> ArrayList<T> readCollectionFromJSONString(String contenuNoeud, T o) {

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;

		ArrayList<T> object = null;
		// String contenuFichier=returnContentOfFileAsString(content);
		// String
		// contenuNoeud=returnContentOfJSONNodeAsString(contenuFichier,"firestations");

		// firestations = objectMapper.readValue(list, FirestationRepository.class);
		try {
			object = objectMapper.readValue(contenuNoeud, new TypeReference<ArrayList<T>>() {
			});
			System.out.println(object.getClass().toString());

			ArrayList l = (ArrayList) object;
			System.out.println("Taille objet:" + l.size());

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}
	
	public static LocalDate formatDateFromString(String stringDate) {
		int year=Integer.parseInt(stringDate.substring(6, 10));
		int month=Integer.parseInt(stringDate.substring(0, 2));
		int day=Integer.parseInt(stringDate.substring(3, 5));
		//System.out.println(stringDate+" :"+year+" "+month+" "+day);
		LocalDate date = LocalDate.of(year, month, day);
		
		return date;

	}

}
