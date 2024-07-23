package com.openclassrooms.p5springbootjg.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.openclassrooms.p5springbootjg.model.MedicalRecord;

public class MedicalRecordModelTest {
	@Test
	public void testGettersAndSetters() {
		try {
			MedicalRecord medicalRecord = new MedicalRecord();
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("peanut");
			allergies.add("milk");
			allergies.add("apple");
			medicalRecord.setAllergies(allergies);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			medicalRecord.setBirthdate(dateFormat.parse("03/06/1989"));
			medicalRecord.setFirstName("John");
			medicalRecord.setLastName("Doe");
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("pharmacol:5000mg");
			medications.add("terazine:10mg");
			medications.add("noznazol:250mg");
			medicalRecord.setMedications(medications);

			assertThat(medicalRecord.getFirstName(), is("John"));
			assertThat(medicalRecord.getLastName(), is("Doe"));
			assertThat(medicalRecord.getBirthdate(), is(dateFormat.parse("03/06/1989")));
			assertThat(medicalRecord.getAllergies(), is(allergies));
			assertThat(medicalRecord.getMedications(), is(medications));
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsAndHashCode() {
		try {
			MedicalRecord medicalRecord1 = new MedicalRecord();
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("peanut");
			allergies.add("milk");
			allergies.add("apple");
			medicalRecord1.setAllergies(allergies);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			medicalRecord1.setBirthdate(dateFormat.parse("03/06/1989"));
			medicalRecord1.setFirstName("John");
			medicalRecord1.setLastName("Doe");
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("pharmacol:5000mg");
			medications.add("terazine:10mg");
			medications.add("noznazol:250mg");
			medicalRecord1.setMedications(medications);

			MedicalRecord medicalRecord2 = new MedicalRecord();
			medicalRecord2.setAllergies(allergies);
			medicalRecord2.setBirthdate(dateFormat.parse("03/06/1989"));
			medicalRecord2.setFirstName("John");
			medicalRecord2.setLastName("Doe");
			medicalRecord2.setMedications(medications);

			assertThat(medicalRecord1, is(medicalRecord2));
			assertThat(medicalRecord1.hashCode(), is(medicalRecord2.hashCode()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testToString() {
		try {
			MedicalRecord medicalRecord = new MedicalRecord();
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("peanut");
			allergies.add("milk");
			allergies.add("apple");
			medicalRecord.setAllergies(allergies);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			medicalRecord.setBirthdate(dateFormat.parse("03/06/1989"));
			medicalRecord.setFirstName("John");
			medicalRecord.setLastName("Doe");
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("pharmacol:5000mg");
			medications.add("terazine:10mg");
			medications.add("noznazol:250mg");
			medicalRecord.setMedications(medications);

			assertThat(medicalRecord.toString(), containsString("firstName=John"));
			assertThat(medicalRecord.toString(), containsString("lastName=Doe"));
			assertThat(medicalRecord.toString(), containsString("birthdate=Sat Jun 03 00:00:00 CEST 1989"));
			assertThat(medicalRecord.toString(),
					containsString("medications=[pharmacol:5000mg, terazine:10mg, noznazol:250mg]"));
			assertThat(medicalRecord.toString(), containsString("allergies=[peanut, milk, apple]"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
