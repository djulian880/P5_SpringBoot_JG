package com.openclassrooms.P5_SpringBoot_JG.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.P5_SpringBoot_JG.model.PersonDTO;
import com.openclassrooms.P5_SpringBoot_JG.service.PersonDTOMapper;
import com.openclassrooms.P5_SpringBoot_JG.Util.ManageRepositoriesFromFile;
import com.openclassrooms.P5_SpringBoot_JG.Util.SafetyNetUtil;

@RestController
public class SafetyNetController {

	@Autowired
	private PersonDTOMapper personDTOMapper;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	// http://localhost:8080/firestation?stationNumber=<station_number>
//		Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers
//		correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les habitants
//		couverts par la station numéro 1. La liste doit inclure les informations spécifiques
//		suivantes : prénom, nom, adresse, numéro de téléphone. De plus, elle doit fournir un
//		décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
//		moins) dans la zone desservie.
	@GetMapping("/firestation")
	ArrayNode returnPersonsBySation(@RequestParam(name = "stationNumber", required = true) Integer stationNumber) {
		logger.debug("Request of data by firestationNumber :"+stationNumber);
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		Integer countOfChildren = 0;
		Integer countOfAdults = 0;
		double age;
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getStation() == stationNumber) {
				ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
				currentNode.put("FirstName", personDTO.getFirstName());
				currentNode.put("LastName", personDTO.getLastName());
				currentNode.put("address", personDTO.getAddress());
				currentNode.put("phone", personDTO.getPhone());
				age = SafetyNetUtil.calculateAge(personDTO.getBirthdate());
				if (age > 18) {
					countOfAdults++;
				} else {
					countOfChildren++;
				}
				resultArray.add(currentNode);
			}
		}
		
		ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
		currentNode.put("countOfChild", countOfChildren);
		currentNode.put("countOfAdults", countOfAdults);
		resultArray.add(currentNode);
		logger.info("Succesful response of data by firestationNumber :"+stationNumber);
		return resultArray;
	}

//		http://localhost:8080/childAlert?address=<address>
//		Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins)
//		habitant à cette adresse. La liste doit comprendre le prénom et le nom de famille de
//		chaque enfant, son âge et une liste des autres membres du foyer. S'il n'y a pas
//		d'enfant, cette url peut renvoyer une chaîne vide.
	@GetMapping("/childAlert")
	ArrayNode returnChildsByAddress(@RequestParam(name = "address", required = true) String address) {
		logger.debug("Request of data for child by address :"+address);
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		List<String> otherMembers=new ArrayList<String>();
	
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getAddress().equals(address)) {
				double age=SafetyNetUtil.calculateAge(personDTO.getBirthdate());
				ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
				if(age<=18) {
					currentNode.put("FirstName", personDTO.getFirstName());
					currentNode.put("LastName", personDTO.getLastName());
					currentNode.put("age", Math.round(SafetyNetUtil.calculateAge(personDTO.getBirthdate())));
					resultArray.add(currentNode);
				}
				else {
					otherMembers.add(personDTO.getFirstName());
				}
			}
		}
		
		if (!resultArray.isEmpty()) {
			ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
			currentNode.set("otherMembers", SafetyNetUtil.listToJSONString(otherMembers));
			resultArray.add(currentNode);
		}
		logger.info("Successful response of data for child by address :"+address);
		return resultArray;
	}

//		http://localhost:8080/phoneAlert?firestation=<firestation_number>
//		Cette url doit retourner une liste des numéros de téléphone des résidents desservis
//		par la caserne de pompiers. Nous l'utiliserons pour envoyer des messages texte
//		d'urgence à des foyers spécifiques.
	@GetMapping("/phoneAlert")
	ArrayNode returnPhonesByStation(@RequestParam(name = "firestation", required = true) Integer stationNumber) {
		logger.debug("Request of data for phone number by firestationNumber :"+stationNumber);
		ArrayList<String> result = new ArrayList<>();
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getStation() == stationNumber) {
				String phone = personDTO.getPhone();
				if (!result.contains(phone)) {
					result.add(phone);
				}
			}
		}
		
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		for (String res : result) {
			ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
			currentNode.put("phone", res);
			resultArray.add(currentNode);
		}
		logger.info("Successful response of data for phone number by firestationNumber :"+stationNumber);
		return resultArray;
	}

//		http://localhost:8080/fire?address=<address>
//		Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le
//		numéro de la caserne de pompiers la desservant. La liste doit inclure le nom, le
//		numéro de téléphone, l'âge et les antécédents médicaux (médicaments, posologie et
//		allergies) de chaque personne.
	@GetMapping("/fire")
	ArrayNode returnPersonsAndStationByAddress(@RequestParam(name = "address", required = true) String address) {
		logger.debug("Request of data for persons by address :"+address);
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getAddress().equals(address)) {
				ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
				currentNode.put("LastName", personDTO.getLastName());
				currentNode.put("phone", personDTO.getPhone());
				currentNode.put("station", String.valueOf(personDTO.getStation()));
				currentNode.set("allergies", SafetyNetUtil.listToJSONString(personDTO.getAllergies()));
				currentNode.set("medications", SafetyNetUtil.listToJSONString(personDTO.getMedications()));
				currentNode.put("age", Math.round(SafetyNetUtil.calculateAge(personDTO.getBirthdate())));
				resultArray.add(currentNode);
			}
		}
		logger.info("Sueccessful response of data for persons by address :"+address);
		return resultArray;
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	// Cette url doit retourner une liste de tous les foyers desservis par la
	// caserne. Cette
	// liste doit regrouper les personnes par adresse. Elle doit aussi inclure le
	// nom, le
	// numéro de téléphone et l'âge des habitants, et faire figurer leurs
	// antécédents
	// médicaux (médicaments, posologie et allergies) à côté de chaque nom.
	@GetMapping("/flood/stations")
	ArrayNode returnHomesAndPersonsMedicalRecordsByStationNumber(
			@RequestParam(name = "stations", required = true) List<Integer> stationNumbers) {
		logger.debug("Request of data for address by stationNumbers :"+stationNumbers);
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		Map <String,List<PersonDTO>> listPersons=new HashMap <String,List<PersonDTO>>();

		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (stationNumbers.contains(personDTO.getStation())) {
				listPersons.computeIfAbsent(personDTO.getAddress(), k -> new ArrayList<>()).add(personDTO);
			}
		}
		  
		for (Map.Entry<String, List<PersonDTO>> entry : listPersons.entrySet()) {
			for (PersonDTO personDTO : entry.getValue()) {
				ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
				currentNode.put("address", personDTO.getAddress());
				currentNode.put("LastName", personDTO.getLastName());
				currentNode.put("phone", personDTO.getPhone());
				currentNode.set("allergies", SafetyNetUtil.listToJSONString(personDTO.getAllergies()));
				currentNode.set("medications", SafetyNetUtil.listToJSONString(personDTO.getMedications()));
				currentNode.put("age", Math.round(SafetyNetUtil.calculateAge(personDTO.getBirthdate())));
				resultArray.add(currentNode);
			}
        }
		logger.info("Successful response of data for address by stationNumbers :"+stationNumbers);
		return resultArray;
	}

	// http://localhost:8080/personInfolastName=<lastName>
	// Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les
	// antécédents
	// médicaux (médicaments, posologie et allergies) de chaque habitant. Si
	// plusieurs
	// personnes portent le même nom, elles doivent toutes apparaître.

	@GetMapping("/")
	ArrayNode returnPersonsByLastName(@RequestParam(name = "personInfolastName", required = true) String lastName) {
		logger.debug("Request of data for address by lastName :"+lastName);
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getLastName().equals(lastName)) {
				ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
				currentNode.put("LastName", personDTO.getLastName());
				currentNode.put("address", personDTO.getAddress());
				currentNode.put("email", personDTO.getEmail());
				currentNode.set("allergies", SafetyNetUtil.listToJSONString(personDTO.getAllergies()));
				currentNode.set("medications", SafetyNetUtil.listToJSONString(personDTO.getMedications()));
				currentNode.put("age", Math.round(SafetyNetUtil.calculateAge(personDTO.getBirthdate())));
				resultArray.add(currentNode);
			}
		}
		logger.info("Successful response of data for address by lastName :"+lastName);
		return resultArray;
	}

	// Cette url doit retourner les adresses mail de tous les habitants de la ville.
	@GetMapping("/communityEmail")
	ArrayNode returnCommunityEmails(@RequestParam(name = "city", required = true) String city) {
		logger.debug("Request of data for mail by city :"+city);
		ArrayList<String> result = new ArrayList<>();
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getCity().equals(city)) {
				String email = personDTO.getEmail();
				if (!result.contains(email)) {
					result.add(email);
				}
			}
		}
		logger.info("Successful response of data for mail by city :"+city);
		return SafetyNetUtil.listSameFieldNameToJSON("email",result);
	}

}
