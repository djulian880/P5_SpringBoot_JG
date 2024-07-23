package com.openclassrooms.p5springbootjg.DTO;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.openclassrooms.p5springbootjg.DTO.PersonDTO;

public class PersonDTOModelTest {
	@Test
	public void testGettersAndSetters() {
		try {
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName("John");
			personDTO.setLastName("Doe");
			personDTO.setAddress("address");
			personDTO.setCity("city");
			personDTO.setEmail("test@email.com");
			personDTO.setPhone("888888");
			personDTO.setZip(12345);
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("peanut");
			allergies.add("milk");
			allergies.add("apple");
			personDTO.setAllergies(allergies);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			personDTO.setBirthdate(dateFormat.parse("03/06/1989"));
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("pharmacol:5000mg");
			medications.add("terazine:10mg");
			medications.add("noznazol:250mg");
			personDTO.setMedications(medications);
			personDTO.setStation(1);

			assertThat(personDTO.getStation(), is(1));
			assertThat(personDTO.getBirthdate(), is(dateFormat.parse("03/06/1989")));
			assertThat(personDTO.getAllergies(), is(allergies));
			assertThat(personDTO.getMedications(), is(medications));
			assertThat(personDTO.getFirstName(), is("John"));
			assertThat(personDTO.getLastName(), is("Doe"));
			assertThat(personDTO.getAddress(), is("address"));
			assertThat(personDTO.getCity(), is("city"));
			assertThat(personDTO.getEmail(), is("test@email.com"));
			assertThat(personDTO.getPhone(), is("888888"));
			assertThat(personDTO.getZip(), is(12345));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsAndHashCode() {
		try {
			PersonDTO personDTO1 = new PersonDTO();
			personDTO1.setFirstName("John");
			personDTO1.setLastName("Doe");
			personDTO1.setAddress("address");
			personDTO1.setCity("city");
			personDTO1.setEmail("test@email.com");
			personDTO1.setPhone("888888");
			personDTO1.setZip(12345);
			ArrayList<String> allergies = new ArrayList<String>();

			allergies.add("peanut");
			allergies.add("milk");
			allergies.add("apple");
			personDTO1.setAllergies(allergies);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			personDTO1.setBirthdate(dateFormat.parse("03/06/1989"));
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("pharmacol:5000mg");
			medications.add("terazine:10mg");
			medications.add("noznazol:250mg");
			personDTO1.setMedications(medications);
			personDTO1.setStation(1);

			PersonDTO personDTO2 = new PersonDTO();
			personDTO2.setFirstName("John");
			personDTO2.setLastName("Doe");
			personDTO2.setAddress("address");
			personDTO2.setCity("city");
			personDTO2.setEmail("test@email.com");
			personDTO2.setPhone("888888");
			personDTO2.setZip(12345);
			personDTO2.setAllergies(allergies);
			personDTO2.setBirthdate(dateFormat.parse("03/06/1989"));
			personDTO2.setMedications(medications);
			personDTO2.setStation(1);

			assertThat(personDTO1, is(personDTO2));
			assertThat(personDTO1.hashCode(), is(personDTO2.hashCode()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testToString() {
		try {
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName("John");
			personDTO.setLastName("Doe");
			personDTO.setAddress("address");
			personDTO.setCity("city");
			personDTO.setEmail("test@email.com");
			personDTO.setPhone("888888");
			personDTO.setZip(12345);
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("peanut");
			allergies.add("milk");
			allergies.add("apple");
			personDTO.setAllergies(allergies);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			personDTO.setBirthdate(dateFormat.parse("03/06/1989"));
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("pharmacol:5000mg");
			medications.add("terazine:10mg");
			medications.add("noznazol:250mg");
			personDTO.setMedications(medications);
			personDTO.setStation(1);

			assertThat(personDTO.toString(), containsString("firstName=John"));
			assertThat(personDTO.toString(), containsString("lastName=Doe"));
			assertThat(personDTO.toString(), containsString("birthdate=Sat Jun 03 00:00:00 CEST 1989"));
			assertThat(personDTO.toString(),
					containsString("medications=[pharmacol:5000mg, terazine:10mg, noznazol:250mg]"));
			assertThat(personDTO.toString(), containsString("allergies=[peanut, milk, apple]"));
			assertThat(personDTO.toString(), containsString("address=address"));
			assertThat(personDTO.toString(), containsString("station=1"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
