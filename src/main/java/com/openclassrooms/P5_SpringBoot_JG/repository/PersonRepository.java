package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
@NoArgsConstructor // <--- THIS is it

@Repository
public class PersonRepo {
	@Autowired

	private static String path = "src/main/resources/data.json";

	public ArrayList<Person> persons;

	public Optional<Person> findByFirstAndLastName(String firstName, String lastName) {
		readFromJson();
		for (Person person : persons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				return Optional.of(person);
			}
		}
		return null;
	}

	public Iterable<Person> findAll() {
		readFromJson();
		return persons;
	}

	public void deleteByFirstAndLastName(String firstName, String lastName) {

		Optional<Person> person = findByFirstAndLastName(firstName, lastName);
		if (person.isPresent()) {
			Person personToDelete = person.get();
			System.out.println("Repo: suppression personne :" + personToDelete.getFirstName());
			persons.remove(personToDelete);
			saveToJson();
		} else {
			// TODO
		}

	}

	public Person add(Person person) {
		readFromJson();
		System.out.println("Repo: ajout personne :" + person.getFirstName());
		persons.add(person);
		saveToJson();
		return persons.get(persons.lastIndexOf(person));
	}

	public Person save(Person p) {
		System.out.println("Repo: modification personne :" + p.getFirstName());
		readFromJson();
		for (Person person : persons) {
			if (person.getFirstName().equals(p.getFirstName()) && person.getLastName().equals(p.getLastName())) {
				person.setAddress(p.getAddress());
				person.setCity(p.getCity());
				person.setEmail(p.getEmail());
				person.setPhone(p.getPhone());
				person.setZip(p.getZip());
				saveToJson();

				System.out.println("Repo: personne trouvée :" + person.getFirstName());
				return person;

			}
		}

		System.out.println("Repo: personne pas trouvée :" + p.getFirstName());

		return null;

	}

	private void saveToJson() {
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode fileContent;

		try {
			fileContent = objectMapper.readTree(ManageRepositoriesFromFile.returnContentOfFileAsString(path));
			String contenu = objectMapper.writeValueAsString(persons);
			JsonNode personsAsJsonNode = objectMapper.readTree(contenu);

			((ObjectNode) fileContent).set("persons", personsAsJsonNode);

			FileWriter file = new FileWriter(path);

			objectMapper.writeValue(file, fileContent);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readPersonsFromJson(String content) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			persons = objectMapper.readValue(content, new TypeReference<ArrayList<Person>>() {
			});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFromJson() {
		readPersonsFromJson(ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(
				ManageRepositoriesFromFile.returnContentOfFileAsString(path), "persons"));
	}
}
