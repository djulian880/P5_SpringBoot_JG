package com.openclassrooms.P5_SpringBoot_JG.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.repository.*;
import lombok.Data;

@Data
@Service
public class PersonService {
	  	@Autowired
	    private PersonRepository personRepository;

	   public Person getPerson(String firstName, String lastName) {
	        return personRepository.findByFirstAndLastName(firstName,lastName);
	    }


	    public Iterable<Person> getPersons() {
	        return personRepository.findAll();
	    }

	    public void deletePerson(String firstName, String lastName) {
	    	personRepository.deleteByFirstAndLastName(firstName,lastName);
	    }

	    public Person savePerson(Person person) {
	    	Person savedPerson = personRepository.save(person);
	        return savedPerson;
	    }
	    
	    public Person addPerson(Person person) {
	    	Person newPerson = personRepository.add(person);
	        return newPerson;
	    }
}
