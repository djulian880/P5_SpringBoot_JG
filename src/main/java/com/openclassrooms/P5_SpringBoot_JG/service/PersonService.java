package com.openclassrooms.P5_SpringBoot_JG.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.repository.*;
import lombok.Data;

@Data
@Service
public class PersonService {
	  	@Autowired
	    private PersonRepo personRepository;

	    public Optional<Person> getPerson(String firstName, String lastName) {
	        return personRepository.findByFirstAndLastName(firstName,lastName);
	    }

	    public Iterable<Person> getPersons() {
	        return personRepository.findAll();
	    }

	    public void deletePerson(String firstName, String lastName) {
	    	System.out.println("Service: delete personne :"+firstName+" "+lastName);
	    	personRepository.deleteByFirstAndLastName(firstName,lastName);
	    }

	    public Person savePerson(Person person) {
	    	Person savedPerson = personRepository.save(person);
	    	System.out.println("Service: update personne :"+person.getFirstName());
	        return savedPerson;
	    }
	    
	    public Person addPerson(Person person) {
	    	System.out.println("Service: update personne :"+person.getFirstName());
	    	Person newPerson = personRepository.add(person);
	        return newPerson;
	    }
}
