package com.openclassrooms.p5springbootjg.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.p5springbootjg.controller.FireStationController;
import com.openclassrooms.p5springbootjg.model.FireStation;
import com.openclassrooms.p5springbootjg.service.FireStationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FireStationService fireStationService;

	@BeforeEach
	private void setUp() {
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
		Mockito.when(fireStationService.getFireStation("112 Steppes Pl")).thenReturn(null);
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}")).andExpect(status().isOk());
	}

	@Test
	public void testPutFireStation() throws Exception {
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}")).andExpect(status().isOk());
	}

	@Test
	public void testDeleteFireStation() throws Exception {
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}")).andExpect(status().isOk());
	}

	@Test
	public void testDeleteFireStationNotFound() throws Exception {
		Mockito.when(fireStationService.getFireStation("112 Steppes Pl")).thenReturn(null);
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}")).andExpect(status().isNotFound());
	}

	@Test
	public void testPostFireStationAlreadyExists() throws Exception {
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"112 Steppes Pl\",\"station\":\"3\"}")).andExpect(status().isForbidden());
	}

}
