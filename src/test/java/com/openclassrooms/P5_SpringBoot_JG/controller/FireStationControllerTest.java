package com.openclassrooms.P5_SpringBoot_JG.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.P5_SpringBoot_JG.controller.FireStationController;
import com.openclassrooms.P5_SpringBoot_JG.controller.PersonController;
import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.service.FireStationService;
import com.openclassrooms.P5_SpringBoot_JG.service.PersonService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class FireStationControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FireStationService fireStationService;

	private void setUp() throws Exception {
		FireStation mockFireStation = new FireStation();
		mockFireStation.setAddress("112 Steppes Pl");
		mockFireStation.setStation(3);
		Mockito.when(fireStationService.getFireStation("112 Steppes Pl")).thenReturn(mockFireStation);
	}

	@Test
	public void testGetFireStations() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk());
	}

	@Test
	public void testPostFireStation() throws Exception {
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPutFireStation() throws Exception {
		setUp();
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteFireStation() throws Exception {
		setUp();
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteFireStationNotFound() throws Exception {
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPostFireStationAlreadyExists() throws Exception {
		setUp();
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}"))
				.andExpect(status().isForbidden());
	}
	
	
}
