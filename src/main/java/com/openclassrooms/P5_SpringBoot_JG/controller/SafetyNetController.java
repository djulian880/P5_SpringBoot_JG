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


	/**
	 * /firestation?stationNumber=<station_number>
	 * search by station number
	 * 
	 * @param stationNumber 
	 * @return list of persons with firstName, lastName, address, phone. And a list of number of children and adults
	 */	

	@GetMapping("/firestation")
	ArrayNode returnPersonsBySation(@RequestParam(name = "stationNumber", required = true) Integer stationNumber) {
		logger.debug("Request of data by firestationNumber :" + stationNumber);
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
		logger.info("Succesful response of data by firestationNumber :" + stationNumber);
		return resultArray;
	}


	/**
	 * /childAlert?address=<address>
	 * search for children by address
	 * 
	 * @param address 
	 * @return list of persons with firstName, lastName, address, age and list of persons living a the same address.
	 */	
	@GetMapping("/childAlert")
	ArrayNode returnChildsByAddress(@RequestParam(name = "address", required = true) String address) {
		logger.debug("Request of data for child by address :" + address);
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		List<String> otherMembers = new ArrayList<String>();

		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getAddress().equals(address)) {
				double age = SafetyNetUtil.calculateAge(personDTO.getBirthdate());
				ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
				if (age <= 18) {
					currentNode.put("FirstName", personDTO.getFirstName());
					currentNode.put("LastName", personDTO.getLastName());
					currentNode.put("age", Math.round(SafetyNetUtil.calculateAge(personDTO.getBirthdate())));
					resultArray.add(currentNode);
				} else {
					otherMembers.add(personDTO.getFirstName());
				}
			}
		}

		if (!resultArray.isEmpty()) {
			ObjectNode currentNode = SafetyNetUtil.getNewObjectNode();
			currentNode.set("otherMembers", SafetyNetUtil.listToJSONString(otherMembers));
			resultArray.add(currentNode);
		}
		logger.info("Successful response of data for child by address :" + address);
		return resultArray;
	}


	/**
	 * /phoneAlert?firestation=<firestation_number>
	 * search for phone numbers by firestation numbers
	 * 
	 * @param firestation_number 
	 * @return list of phone numbers of persons linked to firestation.
	 */	
	@GetMapping("/phoneAlert")
	ArrayNode returnPhonesByStation(@RequestParam(name = "firestation", required = true) Integer stationNumber) {
		logger.debug("Request of data for phone number by firestationNumber :" + stationNumber);
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
		logger.info("Successful response of data for phone number by firestationNumber :" + stationNumber);
		return resultArray;
	}


	/**
	 * /fire?address=<address>
	 * search for persons by address  
	 * 
	 * @param address 
	 * @return list of persons with last name, phone, age, medical records (allergies and medications) and station number
	 */	
	@GetMapping("/fire")
	ArrayNode returnPersonsAndStationByAddress(@RequestParam(name = "address", required = true) String address) {
		logger.debug("Request of data for persons by address :" + address);
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
		logger.info("Sueccessful response of data for persons by address :" + address);
		return resultArray;
	}


	/**
	 * flood/stations?stations=<a list of station_numbers>
	 * search for persons by firestation numbers
	 * 
	 * @param a list of station_numbers 
	 * @return list of persons regrouped by address with last name, phone, age, and medical records
	 */	
	@GetMapping("/flood/stations")
	ArrayNode returnHomesAndPersonsMedicalRecordsByStationNumber(
			@RequestParam(name = "stations", required = true) List<Integer> stationNumbers) {
		logger.debug("Request of data for address by stationNumbers :" + stationNumbers);
		ArrayNode resultArray = SafetyNetUtil.getNewArrayNode();
		Map<String, List<PersonDTO>> listPersons = new HashMap<String, List<PersonDTO>>();

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
		logger.info("Successful response of data for address by stationNumbers :" + stationNumbers);
		return resultArray;
	}


	/**
	 * personInfolastName=<lastName>
	 * search for persons by last name
	 * 
	 * @param lastName 
	 * @return list of persons with last name, address, age, email and medical records
	 */	

	@GetMapping("/")
	ArrayNode returnPersonsByLastName(@RequestParam(name = "personInfolastName", required = true) String lastName) {
		logger.debug("Request of data for address by lastName :" + lastName);
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
		logger.info("Successful response of data for address by lastName :" + lastName);
		return resultArray;
	}


	/**
	 * /communityEmail?city=<city>
	 * search for emails by city
	 * 
	 * @param city 
	 * @return list of emails
	 */	

	@GetMapping("/communityEmail")
	ArrayNode returnCommunityEmails(@RequestParam(name = "city", required = true) String city) {
		logger.debug("Request of data for mail by city :" + city);
		ArrayList<String> result = new ArrayList<>();
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (personDTO.getCity().equals(city)) {
				String email = personDTO.getEmail();
				if (!result.contains(email)) {
					result.add(email);
				}
			}
		}
		logger.info("Successful response of data for mail by city :" + city);
		return SafetyNetUtil.listSameFieldNameToJSON("email", result);
	}

}
