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
		System.out.println("Controller: écriture personne :"+Person.getFirstName());
			return personService.addPerson(Person);
	
	}
	
	
	/**
	 * Update - Update an existing Person
	 * @param id - The id of the Person to update
	 * @param Person - The Person object updated
	 * @return
	 */
	@PutMapping("/person")
	public Person updatePerson(@RequestBody Person Person) {
		System.out.println("Controller: update personne :"+Person.getFirstName());
		Optional<Person> e = personService.getPerson(Person.getFirstName(),Person.getLastName());
		if(e.isPresent()) {
			Person currentPerson = e.get();
			

			System.out.println("Controller: update personne trouvée :"+currentPerson.getFirstName());
			String address = Person.getAddress();
			if(address != null) {
				currentPerson.setAddress(address);
			}
			String city = Person.getCity();
			if(city != null) {
				currentPerson.setCity(city);
			}
			Integer zip = Person.getZip();
			if(zip != null) {
				currentPerson.setZip(zip);
			}
			String phone = Person.getPhone();
			if(phone != null) {
				currentPerson.setPhone(phone);
			}
			String email = Person.getEmail();
			if(email != null) {
				currentPerson.setEmail(email);
			}
			
			
			
			
			return personService.savePerson(currentPerson);
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
			System.out.println("Controller: suppression personne :"+firstName+" "+lastName);
			personService.deletePerson(firstName, lastName);
		}
		
		
	
		/*Long id;
		personService.deletePerson(id);*/
	}
    
    
}