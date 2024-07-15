package com.openclassrooms.P5_SpringBoot_JG;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.repository.FireStationRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.MedicalRecordRepository;
import com.openclassrooms.P5_SpringBoot_JG.service.FireStationService;
import com.openclassrooms.P5_SpringBoot_JG.service.MedicalRecordService;

@SpringBootTest
public class MedicalRecordServiceTest {
	@Autowired
	private MedicalRecordService medicaLRecordService;

	@MockBean
	private MedicalRecordRepository medicaLRecordRepository;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private MedicalRecord mockMedicalRecord = new MedicalRecord();
	private ArrayList<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
	
	private void setUp() throws Exception {
		try {
			Date date = dateFormat.parse("03/06/1989");
			mockMedicalRecord.setBirthdate(date);
		} catch (ParseException e) {

		}
		mockMedicalRecord.setAllergies(new ArrayList<String>());
		ArrayList<String> medications = new ArrayList<String>();
		medications.add("pharmacol:5000mg");
		medications.add("terazine:10mg");
		medications.add("noznazol:250mg");
		mockMedicalRecord.setMedications(medications);
		mockMedicalRecord.setFirstName("Jacob");
		mockMedicalRecord.setLastName("Boyd");

		Mockito.when(medicaLRecordRepository.findByFirstAndLastName("Jacob", "Boyd")).thenReturn(mockMedicalRecord);
		Mockito.when(medicaLRecordRepository.save(mockMedicalRecord)).thenReturn(mockMedicalRecord);
		Mockito.when(medicaLRecordRepository.add(mockMedicalRecord)).thenReturn(mockMedicalRecord);
		
		
		medicalRecords.add(mockMedicalRecord);
		MedicalRecord testMedicalRecord = new MedicalRecord();
		testMedicalRecord.setFirstName("Toto");
		testMedicalRecord.setLastName("Titi");

		medicalRecords.add(testMedicalRecord);
		Mockito.when(medicaLRecordRepository.findAll()).thenReturn(medicalRecords);
	}

	@Test
	public void testgetMeldicalRecord() throws Exception {
		setUp();
		assertEquals(mockMedicalRecord, medicaLRecordService.getMedicalRecord("Jacob", "Boyd"));
	}

	@Test
	public void testdeleteFireStation() throws Exception {
		setUp();
		medicaLRecordService.deleteMedicalRecord("Jacob", "Boyd");
	
	}

	@Test
	public void testsaveFireStation() throws Exception {
		setUp();
		assertEquals(mockMedicalRecord, medicaLRecordService.saveMedicalRecord(mockMedicalRecord));
	}

	@Test
	public void testaddFireStation() throws Exception {
		setUp();
		assertEquals(mockMedicalRecord, medicaLRecordService.addMedicalRecord(mockMedicalRecord));
	}

	@Test
	public void testgetFireStations() throws Exception {
		setUp();
		assertEquals(medicalRecords, medicaLRecordService.getMedicalRecords());
	}

	
	

}
