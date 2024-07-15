package com.openclassrooms.P5_SpringBoot_JG;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import com.openclassrooms.P5_SpringBoot_JG.repository.FireStationRepository;
import com.openclassrooms.P5_SpringBoot_JG.service.FireStationService;

@SpringBootTest

public class FireStationServiceTest {

	@Autowired
	private FireStationService fireStationService;

	@MockBean
	private FireStationRepository fireStationRepository;

	private FireStation mockFireStation = new FireStation();
	private ArrayList<FireStation> fireStations = new ArrayList<FireStation>();
	
	private void setUp() throws Exception {
		
		mockFireStation.setAddress("112 Steppes Pl");
		mockFireStation.setStation(3);
		Mockito.when(fireStationRepository.findByAddress("112 Steppes Pl")).thenReturn(mockFireStation);
		Mockito.when(fireStationRepository.save(mockFireStation)).thenReturn(mockFireStation);
		Mockito.when(fireStationRepository.add(mockFireStation)).thenReturn(mockFireStation);
		
		FireStation testFireStation = new FireStation();
		testFireStation.setAddress("112 Steppes Pl");
		testFireStation.setStation(3);
		fireStations.add(testFireStation);
		testFireStation = new FireStation();
		testFireStation.setAddress("947 E. Rose Dr");
		testFireStation.setStation(1);
		fireStations.add(testFireStation);
		Mockito.when(fireStationRepository.findAll()).thenReturn(fireStations);
	}

	@Test
	public void testgetFireStation() throws Exception {
		setUp();
		assertEquals(mockFireStation, fireStationService.getFireStation("112 Steppes Pl"));
	}

	@Test
	public void testdeleteFireStation() throws Exception {
		setUp();
		fireStationService.deleteFireStation("112 Steppes Pl");
	}

	@Test
	public void testsaveFireStation() throws Exception {
		setUp();
		assertEquals(mockFireStation, fireStationService.saveFireStation(mockFireStation));
	}

	@Test
	public void testaddFireStation() throws Exception {
		setUp();

		assertEquals(mockFireStation, fireStationService.addFireStation(mockFireStation));
	}

	@Test
	public void testgetFireStations() throws Exception {
		setUp();
		assertEquals(fireStations, fireStationService.getFireStations());
	}

}
