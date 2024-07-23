package com.openclassrooms.p5springbootjg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.p5springbootjg.DTO.PersonDTO;
import com.openclassrooms.p5springbootjg.DTO.PersonDTOMapper;
import com.openclassrooms.p5springbootjg.Util.JsonTools;
import com.openclassrooms.p5springbootjg.controller.PersonController;
import com.openclassrooms.p5springbootjg.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class SafetyNetService {

	@Autowired
	private PersonDTOMapper personDTOMapper;

	private static Logger logger = LoggerFactory.getLogger(SafetyNetService.class);

	public ArrayList<PersonDTO> filterPersonsDTO(String fieldName, String value) {
		ArrayList<PersonDTO> resultArray = new ArrayList<PersonDTO>();

		switch (fieldName) {
		case "stationNumber":

			for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
				if (personDTO.getStation() == Integer.parseInt(value)) {
					resultArray.add(personDTO);
				}
			}
			break;
		case "address":

			for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
				if (personDTO.getAddress().equals(value)) {
					resultArray.add(personDTO);
				}
			}
			break;
		case "lastName":

			for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
				if (personDTO.getLastName().equals(value)) {
					resultArray.add(personDTO);
				}
			}
			break;
		case "city":

			for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
				if (personDTO.getCity().equals(value)) {
					resultArray.add(personDTO);
				}
			}
			break;

		}
		return resultArray;
	}

	public ObjectNode returnPersonsByStation(Integer stationNumber) {
		ObjectNode resultNode = JsonTools.getNewObjectNode();
		ArrayNode dataArray = JsonTools.getNewArrayNode();

		Integer countOfChildren = 0;
		Integer countOfAdults = 0;
		for (PersonDTO personDTO : filterPersonsDTO("stationNumber", stationNumber.toString())) {
			ObjectNode currentNode = JsonTools.getNewObjectNode();
			currentNode.put("FirstName", personDTO.getFirstName());
			currentNode.put("LastName", personDTO.getLastName());
			currentNode.put("address", personDTO.getAddress());
			currentNode.put("phone", personDTO.getPhone());
			if (personDTO.getAge() > 18) {
				countOfAdults++;
			} else {
				countOfChildren++;
			}
			dataArray.add(currentNode);
		}
		resultNode.put("countOfChildren", countOfChildren);
		resultNode.put("countOfAdults", countOfAdults);
		resultNode.set("DATA", dataArray);
		logger.info("Successful response of data by firestationNumber :" + stationNumber);
		return resultNode;
	}

	public ObjectNode returnChildrenByAddress(String address) {
		ObjectNode resultNode = JsonTools.getNewObjectNode();
		ArrayNode dataArray = JsonTools.getNewArrayNode();

		List<PersonDTO> otherInhabitants = new ArrayList<PersonDTO>();

		for (PersonDTO personDTO : filterPersonsDTO("address", address)) {

			double age = personDTO.getAge();
			ObjectNode currentNode = JsonTools.getNewObjectNode();
			if (age <= 18) {
				currentNode.put("FirstName", personDTO.getFirstName());
				currentNode.put("LastName", personDTO.getLastName());
				currentNode.put("age", Math.round(personDTO.getAge()));
				dataArray.add(currentNode);
			} else {
				otherInhabitants.add(personDTO);
			}

		}

		if (!dataArray.isEmpty()) {
			ArrayNode otherMembersArray = JsonTools.getNewArrayNode();
			for (PersonDTO personDTO : otherInhabitants) {
				ObjectNode currentNode = JsonTools.getNewObjectNode();

				currentNode.put("FirstName", personDTO.getFirstName());
				currentNode.put("LastName", personDTO.getLastName());

				otherMembersArray.add(currentNode);
			}
			resultNode.set("otherInhabitants", otherMembersArray);

		}

		resultNode.set("DATA", dataArray);
		logger.info("Successful response of data for children by address :" + address);
		return resultNode;

	}

	public ObjectNode returnPhonesByStation(Integer stationNumber) {

		ObjectNode resultNode = JsonTools.getNewObjectNode();

		ArrayList<String> resultPhones = new ArrayList<>();

		// Build list of phones and avoid duplicates
		for (PersonDTO personDTO : filterPersonsDTO("stationNumber", stationNumber.toString())) {
			String phone = personDTO.getPhone();
			if (!resultPhones.contains(phone)) {
				resultPhones.add(phone);
			}
		}

		// Convert this list in JSON
		ArrayNode dataArray = JsonTools.convertArrayStringToJsonNode(resultPhones,"phone");
		resultNode.set("DATA", dataArray);
		logger.info("Successful response of data for phone number by firestationNumber :" + stationNumber);
		return resultNode;
	}

	public ObjectNode returnPersonsAndStationByAddress(String address) {
		ObjectNode resultNode = JsonTools.getNewObjectNode();
		ArrayNode dataArray = JsonTools.getNewArrayNode();

		for (PersonDTO personDTO : filterPersonsDTO("address", address)) {

			ObjectNode currentNode = JsonTools.getNewObjectNode();
			currentNode.put("LastName", personDTO.getLastName());
			currentNode.put("phone", personDTO.getPhone());
			currentNode.put("station", String.valueOf(personDTO.getStation()));
			currentNode.set("allergies", JsonTools.listToJSONString(personDTO.getAllergies()));
			currentNode.set("medications", JsonTools.listToJSONString(personDTO.getMedications()));
			currentNode.put("age", Math.round(personDTO.getAge()));
			dataArray.add(currentNode);

		}
		resultNode.set("DATA", dataArray);
		logger.info("Successful response of data for persons by address :" + address);
		return resultNode;
	}

	public ObjectNode returnHomesAndPersonsMedicalRecordsByStationNumber(List<Integer> stationNumbers) {
		ObjectNode resultNode = JsonTools.getNewObjectNode();
		ArrayNode dataArray = JsonTools.getNewArrayNode();
		Map<String, List<PersonDTO>> listPersons = new HashMap<String, List<PersonDTO>>();
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			if (stationNumbers.contains(personDTO.getStation())) {
				listPersons.computeIfAbsent(personDTO.getAddress(), k -> new ArrayList<>()).add(personDTO);
			}
		}

		for (Map.Entry<String, List<PersonDTO>> entry : listPersons.entrySet()) {
			for (PersonDTO personDTO : entry.getValue()) {
				ObjectNode currentNode = JsonTools.getNewObjectNode();
				currentNode.put("address", personDTO.getAddress());
				currentNode.put("LastName", personDTO.getLastName());
				currentNode.put("phone", personDTO.getPhone());
				currentNode.set("allergies", JsonTools.listToJSONString(personDTO.getAllergies()));
				currentNode.set("medications", JsonTools.listToJSONString(personDTO.getMedications()));
				currentNode.put("age", Math.round(personDTO.getAge()));
				dataArray.add(currentNode);
			}
		}

		resultNode.set("DATA", dataArray);
		logger.info("Successful response of data for address by stationNumbers :" + stationNumbers);
		return resultNode;
	}

	public ObjectNode returnPersonsByLastName(String lastName) {
		ObjectNode resultNode = JsonTools.getNewObjectNode();
		ArrayNode dataArray = JsonTools.getNewArrayNode();
		for (PersonDTO personDTO : filterPersonsDTO("lastName", lastName)) {

			ObjectNode currentNode = JsonTools.getNewObjectNode();
			currentNode.put("LastName", personDTO.getLastName());
			currentNode.put("address", personDTO.getAddress());
			currentNode.put("email", personDTO.getEmail());
			currentNode.set("allergies", JsonTools.listToJSONString(personDTO.getAllergies()));
			currentNode.set("medications", JsonTools.listToJSONString(personDTO.getMedications()));
			currentNode.put("age", Math.round(personDTO.getAge()));
			dataArray.add(currentNode);

		}
		resultNode.set("DATA", dataArray);
		logger.info("Successful response of data for address by lastName :" + lastName);
		return resultNode;
	}

	public ObjectNode returnCommunityEmails(String city) {
		ObjectNode resultNode = JsonTools.getNewObjectNode();
		ArrayList<String> resultEmails = new ArrayList<>();

		// Build a list to avoid duplicates
		for (PersonDTO personDTO : filterPersonsDTO("city", city)) {
			String email = personDTO.getEmail();
			if (!resultEmails.contains(email)) {
				resultEmails.add(email);
			}
		}
		// Convert this list in JSON
		ArrayNode dataArray = JsonTools.convertArrayStringToJsonNode(resultEmails,"email");
		resultNode.set("DATA", dataArray);
		logger.info("Successful response of data for mail by city :" + city);
		return resultNode;
	}

}
