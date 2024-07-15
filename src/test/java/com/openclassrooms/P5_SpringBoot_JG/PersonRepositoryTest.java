package com.openclassrooms.P5_SpringBoot_JG;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.P5_SpringBoot_JG.Util.ManageRepositoriesFromFile;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.repository.PersonRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonRepositoryTest {
	@Autowired
	private PersonRepository personRepository;

	private static String path = "src/main/resources/data.json";

	@Test
	@Order(1)
	public void AddPersonTest() {
		Person mockPerson = new Person();
		mockPerson.setFirstName("TestFirstName");
		mockPerson.setLastName("TestLastName");
		mockPerson.setAddress("1509 Culver St");
		mockPerson.setCity("Culver");
		mockPerson.setEmail("test@test.com");
		mockPerson.setPhone("841-111-1111");
		mockPerson.setZip(12345);

		Person result = personRepository.add(mockPerson);
		assertThat(mockPerson, is(result));
		String fileResult = ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(
				ManageRepositoriesFromFile.returnContentOfFileAsString(path), "persons");
		assertThat(fileResult, containsString("TestFirstName"));
	}

	@Test
	@Order(2)
	public void SavePersonTest() {
		Person mockPerson = new Person();
		mockPerson.setFirstName("TestFirstName");
		mockPerson.setLastName("TestLastName");
		mockPerson.setAddress("1509 Culver St");
		mockPerson.setCity("TestCityUpdated");
		mockPerson.setEmail("test@test.com");
		mockPerson.setPhone("841-111-1111");
		mockPerson.setZip(12345);

		Person result = personRepository.save(mockPerson);
		assertThat(mockPerson, is(result));
		String fileResult = ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(
				ManageRepositoriesFromFile.returnContentOfFileAsString(path), "persons");
		assertThat(fileResult, containsString("TestCityUpdated"));
	}

	@Test
	@Order(3)
	public void DeletePersonTest() {
		Person mockPerson = new Person();
		mockPerson.setFirstName("TestFirstName");
		mockPerson.setLastName("TestLastName");
		personRepository.deleteByFirstAndLastName("TestFirstName", "TestLastName");
		String fileResult = ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(
				ManageRepositoriesFromFile.returnContentOfFileAsString(path), "persons");
		assertThat(fileResult, not(containsString("TestCityUpdated")));
	}

	@Test
	@Order(4)
	public void GetAllPersonsTest() {
		Iterable<Person> persons = personRepository.findAll();
		int count = 0;
		for (Person element : persons) {
			count++;
		}
		assertThat(count, is(equalTo(23)));

	}

	@Test
	@Order(5)
	public void SavePersonFailedTest() {
		Person mockPerson = new Person();
		mockPerson.setFirstName("TestFirstName");
		mockPerson.setLastName("TestLastName");
		assertThat(personRepository.save(mockPerson), nullValue());
	}
}
