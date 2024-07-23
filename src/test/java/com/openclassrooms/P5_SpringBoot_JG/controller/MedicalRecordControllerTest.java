package com.openclassrooms.P5_SpringBoot_JG.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.service.MedicalRecordService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebMvcTest(controllers = MedicalRecordController.class)

public class MedicalRecordControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordService medicalRecordService;

	@BeforeEach
	private void setUp() throws Exception {
		MedicalRecord mockMedicalRecord = new MedicalRecord();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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

		Mockito.when(medicalRecordService.getMedicalRecord("Jacob", "Boyd")).thenReturn(mockMedicalRecord);
	}

	@Test
	public void testGetMedicalRecords() throws Exception {
		mockMvc.perform(get("/medicalRecords")).andExpect(status().isOk());
	}

	@Test
	public void testPostMedicalRecord() throws Exception {
		Mockito.when(medicalRecordService.getMedicalRecord("Jacob", "Boyd")).thenReturn(null);
		mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"birthdate\":\"1989-06-03T23:00:00.000+00:00\","
						+ "\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPutMedicalRecord() throws Exception {
		mockMvc.perform(put("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"birthdate\":\"1989-06-03T23:00:00.000+00:00\","
						+ "\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteMedicalRecord() throws Exception {
		mockMvc.perform(delete("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"birthdate\":\"1989-06-03T23:00:00.000+00:00\","
						+ "\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]}"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteMedicalRecordNotFound() throws Exception {
		Mockito.when(medicalRecordService.getMedicalRecord("Jacob", "Boyd")).thenReturn(null);
		mockMvc.perform(delete("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"birthdate\":\"1989-06-03T23:00:00.000+00:00\","
						+ "\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]}"))
				.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void testPostMedicalRecordAlreadyExists() throws Exception {
		mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"birthdate\":\"1989-06-03T23:00:00.000+00:00\","
						+ "\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]}"))
				.andExpect(status().isForbidden());
	}
}
