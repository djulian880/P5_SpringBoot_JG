package com.openclassrooms.P5_SpringBoot_JG.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	/**
	 * Read - Get all persons
	 * 
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/persons")
	public Iterable<Person> getPersons() {
		return personService.getPersons();
	}

	/**
	 * Create - Add a new Person
	 * 
	 * @param Person the object Person to be created
	 * @return The Person object saved
	 */
	@PostMapping("/person")
	@ResponseStatus(HttpStatus.OK)
	public Person createPerson(@RequestBody Person Person) {
		logger.trace("Controller: écriture personne :" + Person.getFirstName());

		Optional<Person> e = Optional.ofNullable(personService.getPerson(Person.getFirstName(), Person.getLastName()));
		if (e.isEmpty()) {
			return personService.addPerson(Person);
		} else {
			logger.error(
					"Cette personne existe déjà dans la BDD:" + Person.getFirstName() + " " + Person.getLastName());
			throw new PersonAlreadyExistsException();
		}
	}

	/**
	 * Update - Update an existing Person
	 * 
	 * @param Person - The Person object updated
	 * @return Person - The Person object updated
	 */
	@PutMapping("/person")
	@ResponseStatus(HttpStatus.OK)
	public Person updatePerson(@RequestBody Person Person) {
		logger.trace("Controller: update personne :" + Person.getFirstName());
		Optional<Person> personFound = Optional
				.ofNullable(personService.getPerson(Person.getFirstName(), Person.getLastName()));
		if (personFound.isPresent()) {
			Person currentPerson = personFound.get();
			logger.trace("Mise à jour personne trouvée :" + currentPerson.getFirstName());
			String address = Person.getAddress();
			if (address != null) {
				currentPerson.setAddress(address);
			}
			String city = Person.getCity();
			if (city != null) {
				currentPerson.setCity(city);
			}
			Integer zip = Person.getZip();
			if (zip != null) {
				currentPerson.setZip(zip);
			}
			String phone = Person.getPhone();
			if (phone != null) {
				currentPerson.setPhone(phone);
			}
			String email = Person.getEmail();
			if (email != null) {
				currentPerson.setEmail(email);
			}
			return personService.savePerson(currentPerson);
		} else {

			logger.error("Personne non touvée dans la BDD:" + Person.getFirstName() + " " + Person.getLastName());
			throw new PersonNotFoundException();

		}
	}

	/**
	 * Delete - Delete an Person
	 * 
	 * @param Person - The Person object updated
	 * 
	 */
	@DeleteMapping("/person")
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@RequestBody Person Person) {
		Optional<Person> personFound = Optional
				.ofNullable(personService.getPerson(Person.getFirstName(), Person.getLastName()));
		if (personFound.isPresent()) {
			logger.trace("Suppression personne :" + Person.getFirstName() + " " + Person.getLastName());
			personService.deletePerson(Person.getFirstName(), Person.getLastName());
		} else {
			logger.error("Personne non touvée dans la BDD:" + Person.getFirstName() + " " + Person.getLastName());
			throw new PersonNotFoundException();
		}

	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	public static class PersonAlreadyExistsException extends RuntimeException {
		private static final long serialVersionUID = 1239216111432570194L;

		public PersonAlreadyExistsException() {
			super("Person already exists");
		}
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class PersonNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1239216111432570194L;

		public PersonNotFoundException() {
			super("Person not found");
		}
	}

}