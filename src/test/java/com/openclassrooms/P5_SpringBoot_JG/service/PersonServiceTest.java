package com.openclassrooms.P5_SpringBoot_JG.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.repository.PersonRepository;
import com.openclassrooms.P5_SpringBoot_JG.service.PersonService;

@SpringBootTest
public class PersonServiceTest {
	@Autowired
	private PersonService personService;

	@MockBean
	private PersonRepository personRepository;

private Person mockPerson = new Person();
private ArrayList<Person> persons = new ArrayList<Person>();
	
	private void setUp() throws Exception {
		
		mockPerson.setFirstName("Jacob");
		mockPerson.setLastName("Boyd");
		mockPerson.setAddress("1509 Culver St");
		mockPerson.setCity("Culver");
		mockPerson.setEmail("jaboyd@email.com");
		mockPerson.setPhone("841-111-1111");
		mockPerson.setZip(12345);
		
		

		Mockito.when(personRepository.findByFirstAndLastName("Jacob","Boyd")).thenReturn(mockPerson);
		Mockito.when(personRepository.save(mockPerson)).thenReturn(mockPerson);
		Mockito.when(personRepository.add(mockPerson)).thenReturn(mockPerson);
		
		persons.add(mockPerson);
		Person testPerson = new Person();
		testPerson.setFirstName("toto");
		testPerson.setLastName("titi");
		persons.add(testPerson);
		Mockito.when(personRepository.findAll()).thenReturn(persons);
	}

	@Test
	public void testgetPerson() throws Exception {
		setUp();

		assertEquals(mockPerson, personService.getPerson("Jacob","Boyd"));
	}

	@Test
	public void testdeletePerson() throws Exception {
		setUp();
		personService.deletePerson("Jacob","Boyd");
	}

	@Test
	public void testsavePerson() throws Exception {
		setUp();
		assertEquals(mockPerson, personService.savePerson(mockPerson));
	}

	@Test
	public void testaddPerson() throws Exception {
		setUp();

		assertEquals(mockPerson, personService.addPerson(mockPerson));
	}

	@Test
	public void testgetPersons() throws Exception {
		setUp();
		assertEquals(persons, personService.getPersons());
	}

}
