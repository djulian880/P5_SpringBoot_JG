package com.openclassrooms.p5springbootjg.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.p5springbootjg.controller.PersonController;
import com.openclassrooms.p5springbootjg.model.Person;
import com.openclassrooms.p5springbootjg.service.PersonService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;
	
	@BeforeEach
	private void setUp() throws Exception {
		Person mockPerson = new Person();
		mockPerson.setFirstName("Jacob");
		mockPerson.setLastName("Boyd");
		mockPerson.setAddress("1509 Culver St");
		mockPerson.setCity("Culver");
		mockPerson.setEmail("jaboyd@email.com");
		mockPerson.setPhone("841-111-1111");
		mockPerson.setZip(12345);
		Mockito.when(personService.getPerson("Jacob", "Boyd")).thenReturn(mockPerson);
	}

	@Test
	public void testGetPersons() throws Exception {
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}

	@Test
	public void testPostPerson() throws Exception {
		Mockito.when(personService.getPerson("Jacob", "Boyd")).thenReturn(null);
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"1509 Culver St\","
						+ "\"city\":\"Culver\",\"zip\":12345,\"phone\":\"841-111-1111\","
						+ "\"email\":\"jaboyd@email.com\",\"firstName\":\"toto\",\"lastName\":\"titi\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPutPerson() throws Exception {
		mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"1509 Culver St\","
						+ "\"city\":\"Culver\",\"zip\":12345,\"phone\":\"841-111-1111\","
						+ "\"email\":\"jaboyd@email.com\",\"firstName\":\"Jacob\",\"lastName\":\"Boyd\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"1509 Culver St\","
						+ "\"city\":\"Culver\",\"zip\":12345,\"phone\":\"841-111-1111\","
						+ "\"email\":\"jaboyd@email.com\",\"firstName\":\"Jacob\",\"lastName\":\"Boyd\"}"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDeletePersonNotFound() throws Exception {
		Mockito.when(personService.getPerson("Jacob", "Boyd")).thenReturn(null);
		mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"1509 Culver St\","
						+ "\"city\":\"Culver\",\"zip\":12345,\"phone\":\"841-111-1111\","
						+ "\"email\":\"jaboyd@email.com\",\"firstName\":\"Jacob\",\"lastName\":\"Boyd\"}"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testPostPersonAlreadyExists() throws Exception {
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"1509 Culver St\","
						+ "\"city\":\"Culver\",\"zip\":12345,\"phone\":\"841-111-1111\","
						+ "\"email\":\"jaboyd@email.com\",\"firstName\":\"Jacob\",\"lastName\":\"Boyd\"}"))
				.andExpect(status().isForbidden());
	}

}
