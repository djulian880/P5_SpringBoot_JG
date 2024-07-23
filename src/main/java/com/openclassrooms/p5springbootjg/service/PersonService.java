package com.openclassrooms.p5springbootjg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.p5springbootjg.model.Person;
import com.openclassrooms.p5springbootjg.repository.*;

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
