package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.P5_SpringBoot_JG.Util.ManageRepositoriesFromFile;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Repository
public class PersonRepository {
	@Autowired

	private static Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	private static String path = "src/main/resources/data.json";

	public ArrayList<Person> persons;

	private ObjectMapper objectMapper = new ObjectMapper();

	public Person findByFirstAndLastName(String firstName, String lastName) {
		readFromJson();
		for (Person person : persons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
	}

	public Iterable<Person> findAll() {
		readFromJson();
		return persons;
	}

	public void deleteByFirstAndLastName(String firstName, String lastName) {
		Optional<Person> person = Optional.ofNullable(findByFirstAndLastName(firstName, lastName));
		if (person.isPresent()) {
			Person personToDelete = person.get();
			logger.debug("deletion of person :" + personToDelete.getFirstName());
			persons.remove(personToDelete);
			saveToJson();
		} else {
			logger.error("Person not found: " + firstName + " " + lastName);
		}

	}

	public Person add(Person person) {
		readFromJson();
		logger.debug("creation of person :" + person.getFirstName());
		persons.add(person);
		saveToJson();
		return persons.get(persons.lastIndexOf(person));
	}

	public Person save(Person p) {
		logger.debug("Update of person :" + p.getFirstName());
		readFromJson();
		for (Person person : persons) {
			if (person.getFirstName().equals(p.getFirstName()) && person.getLastName().equals(p.getLastName())) {
				person.setAddress(p.getAddress());
				person.setCity(p.getCity());
				person.setEmail(p.getEmail());
				person.setPhone(p.getPhone());
				person.setZip(p.getZip());
				saveToJson();
				logger.debug("person found :" + person.getFirstName());
				return person;
			}
		}
		logger.error("person not found :" + p.getFirstName());
		return null;
	}

	private void saveToJson() {
		try {
			JsonNode fileContent = objectMapper.readTree(ManageRepositoriesFromFile.returnContentOfFileAsString(path));
			String contenu = objectMapper.writeValueAsString(persons);
			JsonNode personsAsJsonNode = objectMapper.readTree(contenu);
			((ObjectNode) fileContent).set("persons", personsAsJsonNode);
			FileWriter file = new FileWriter(path);
			objectMapper.writeValue(file, fileContent);
		} catch (JsonMappingException e) {
			logger.error(e.toString());
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}

	}

	public void readPersonsFromJson(String content) {
		try {
			persons = objectMapper.readValue(content, new TypeReference<ArrayList<Person>>() {
			});
		} catch (JsonMappingException e) {
			logger.error(e.toString());
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		}
	}

	public void readFromJson() {
		readPersonsFromJson(ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(
				ManageRepositoriesFromFile.returnContentOfFileAsString(path), "persons"));
	}
}
