package com.openclassrooms.P5_SpringBoot_JG;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SafetyNetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testEndPointFirestation() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=3").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("Boyd")))
				.andExpect(content().string(containsString("Shepard")))
				.andExpect(content().string(containsString("Foster")));

	}

	@Test
	public void testEndPointChildAlert() throws Exception {
		mockMvc.perform(get("/childAlert?address=1509 Culver St").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(content().string(containsString("Boyd"))).andExpect(status().isOk());

	}

	@Test
	public void testEndPointPhoneAlert() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=2").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().string(containsString("841-874-6513")))
				.andExpect(content().string(containsString("841-874-7878")))
				.andExpect(content().string(containsString("841-874-7512")))
				.andExpect(content().string(containsString("841-874-7458"))).andExpect(status().isOk());

	}

	@Test
	public void testEndPointFire() throws Exception {
		mockMvc.perform(get("/fire?address=1509 Culver St").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().string(containsString("841-874-6544")))
				.andExpect(content().string(containsString("noznazol:250mg")))
				.andExpect(content().string(containsString("xilliathal"))).andExpect(status().isOk());

	}

	@Test
	public void testEndPointFlood() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().string(containsString("947 E. Rose Dr")))
				.andExpect(content().string(containsString("shellfish")))
				.andExpect(content().string(containsString("Peters"))).andExpect(status().isOk());

	}

	@Test
	public void testEndPointPersonInfo() throws Exception {
		mockMvc.perform(get("/?personInfolastName=Boyd").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().string(containsString("nillacilan")))
				.andExpect(content().string(containsString("1509 Culver St")))
				.andExpect(content().string(containsString("peanut"))).andExpect(status().isOk());

	}

	@Test
	public void testEndPointCommunityEmail() throws Exception {
		mockMvc.perform(get("/communityEmail?city=Culver").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().string(containsString("ssanw@email.com")))
				.andExpect(content().string(containsString("tenz@email.com")))
				.andExpect(content().string(containsString("zarc@email.com")))
				.andExpect(content().string(containsString("aly@imail.com")))
				.andExpect(content().string(containsString("gramps@email.com")))

				.andExpect(status().isOk());

	}

}
