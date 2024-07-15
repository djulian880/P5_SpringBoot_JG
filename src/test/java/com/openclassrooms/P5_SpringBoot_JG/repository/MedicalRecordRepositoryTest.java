package com.openclassrooms.P5_SpringBoot_JG.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.P5_SpringBoot_JG.Util.ManageJsonFile;
import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.repository.MedicalRecordRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalRecordRepositoryTest {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	private static String path = "src/main/resources/data.json";

	@Test
	@Order(1)
	public void AddMedicalRecordTest() {
		MedicalRecord mockMedicalRecord = new MedicalRecord();
		mockMedicalRecord.setAllergies(new ArrayList<String>());
		ArrayList<String> medications = new ArrayList<String>();
		medications.add("pharmacol:5000mg");
		medications.add("terazine:10mg");
		medications.add("noznazol:250mg");
		mockMedicalRecord.setMedications(medications);
		mockMedicalRecord.setFirstName("TestFirstName");
		mockMedicalRecord.setLastName("TestLastName");
		MedicalRecord result = medicalRecordRepository.add(mockMedicalRecord);
		assertThat(mockMedicalRecord, is(result));
		String fileResult = ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "medicalrecords");
		assertThat(fileResult, containsString("TestFirstName"));
	}

	@Test
	@Order(2)
	public void SaveMedicalRecordTest() {
		MedicalRecord mockMedicalRecord = new MedicalRecord();
		mockMedicalRecord.setAllergies(new ArrayList<String>());
		ArrayList<String> medications = new ArrayList<String>();
		medications.add("medictest1:5000mg");
		medications.add("terazine:10mg");
		medications.add("noznazol:250mg");
		mockMedicalRecord.setMedications(medications);
		mockMedicalRecord.setFirstName("TestFirstName");
		mockMedicalRecord.setLastName("TestLastName");
		MedicalRecord result = medicalRecordRepository.save(mockMedicalRecord);

		assertThat(mockMedicalRecord, is(result));
		String fileResult = ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "medicalrecords");
		assertThat(fileResult, containsString("medictest1:5000mg"));

	}

	@Test
	@Order(3)
	public void DeleteMedicalRecordTest() {
		MedicalRecord mockMedicalRecord = new MedicalRecord();
		mockMedicalRecord.setFirstName("TestFirstName");
		mockMedicalRecord.setLastName("TestLastName");
		medicalRecordRepository.deleteByFirstAndLastName("TestFirstName", "TestLastName");
		String fileResult = ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "medicalrecords");
		assertThat(fileResult, not(containsString("medictest1:5000mg")));
	}

	@Test
	@Order(4)
	public void GetAllMedicalRecordsTest() {
		Iterable<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
		int count = 0;
		for (MedicalRecord element : medicalRecords) {
			count++;
		}
		assertThat(count, is(equalTo(23)));

	}

	@Test
	@Order(5)
	public void SaveMedicalRecordFailedTest() {
		MedicalRecord mockMedicalRecord = new MedicalRecord();
		mockMedicalRecord.setFirstName("TestFirstName");
		mockMedicalRecord.setLastName("TestLastName");
		assertThat(medicalRecordRepository.save(mockMedicalRecord), nullValue());

	}
}
