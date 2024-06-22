package com.openclassrooms.P5_SpringBoot_JG.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.service.PersonService;

import lombok.*;

@RestController
public class PersonController {
	@Autowired
    private PersonService personService;

	
	
    /**
    * Read - Get all employees
    * @return - An Iterable object of Employee full filled
    */
    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }
    
    /**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	/*@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") final Long id) {
		Optional<Employee> employee = employeeService.getEmployee(id);
		if(employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}*/
    
	/**
	 * Create - Add a new Person
	 * @param Person An object Person
	 * @return The Person object saved
	 */
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person Person) {
		
			return personService.savePerson(Person);
	
	}
	
	
	/**
	 * Update - Update an existing Person
	 * @param id - The id of the Person to update
	 * @param Person - The Person object updated
	 * @return
	 */
	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable("id") final Long id, @RequestBody Person Person) {
		Optional<Person> e = personService.getPerson(id);
		if(e.isPresent()) {
			Person currentPerson = e.get();
			
			/*public String firstName;
			public String lastName;
			public String address;
			public String city;
			public int zip;
			public String phone;
			public String email;
			*/
			
			String firstName = Person.getFirstName();
			if(firstName != null) {
				currentPerson.setFirstName(firstName);
			}
			String lastName = Person.getLastName();
			if(lastName != null) {
				currentPerson.setLastName(lastName);;
			}
	
			
			personService.savePerson(currentPerson);
			return currentPerson;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an Person
	 * @param id - The id of the Person to delete
	 */
	@DeleteMapping("/person")
	public void deletePerson(@RequestBody Person Person) {
		String firstName = Person.getFirstName();
		String lastName = Person.getLastName();
		
		if(firstName != null && lastName != null ) {
	
		}
		
		
		System.out.println("Personne récupérée");
		System.out.println(Person.getFirstName());
		System.out.println(Person.getLastName());
		System.out.println(Person.getEmail());
		/*Long id;
		personService.deletePerson(id);*/
	}
    
    
}