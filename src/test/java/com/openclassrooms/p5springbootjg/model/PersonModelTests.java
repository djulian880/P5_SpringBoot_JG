package com.openclassrooms.p5springbootjg.model;

import org.junit.jupiter.api.Test;

import com.openclassrooms.p5springbootjg.model.Person;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class PersonModelTests {

	@Test
	public void testGettersAndSetters() {
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Doe");
		person.setAddress("address");
		person.setCity("city");
		person.setEmail("test@email.com");
		person.setPhone("888888");
		person.setZip(12345);

		assertThat(person.getFirstName(), is("John"));
		assertThat(person.getLastName(), is("Doe"));
		assertThat(person.getAddress(), is("address"));
		assertThat(person.getCity(), is("city"));
		assertThat(person.getEmail(), is("test@email.com"));
		assertThat(person.getPhone(), is("888888"));
		assertThat(person.getZip(), is(12345));
	}

	@Test
	public void testEqualsAndHashCode() {
		Person person1 = new Person();
		person1.setFirstName("John");
		person1.setLastName("Doe");
		person1.setAddress("address");
		person1.setCity("city");
		person1.setEmail("test@email.com");
		person1.setPhone("888888");
		person1.setZip(12345);

		Person person2 = new Person();
		person2.setFirstName("John");
		person2.setLastName("Doe");
		person2.setAddress("address");
		person2.setCity("city");
		person2.setEmail("test@email.com");
		person2.setPhone("888888");
		person2.setZip(12345);

		assertThat(person1, is(person2));
		assertThat(person1.hashCode(), is(person2.hashCode()));
	}

	@Test
	public void testToString() {
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Doe");

		assertThat(person.toString(), containsString("firstName=John"));
		assertThat(person.toString(), containsString("lastName=Doe"));
	}
}
