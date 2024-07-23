package com.openclassrooms.p5springbootjg.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.p5springbootjg.Util.ManageJsonFile;
import com.openclassrooms.p5springbootjg.model.FireStation;
import com.openclassrooms.p5springbootjg.repository.FireStationRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FireStationRepositoryTest {

	@Autowired
	private FireStationRepository fireStationRepository;

	private static String path = "src/main/resources/data.json";

	@Test
	@Order(1)
	public void AddFirestationTest() {
		FireStation mockFireStation = new FireStation();
		mockFireStation.setAddress("10 test Street");
		mockFireStation.setStation(6);
		FireStation result = fireStationRepository.add(mockFireStation);
		assertThat(mockFireStation, is(result));
		String fileResult = ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "firestations");
		assertThat(fileResult, containsString("\"address\":\"10 test Street\",\"station\":6"));
	}

	@Test
	@Order(2)
	public void SaveFirestationTest() {
		FireStation mockFireStation = new FireStation();
		mockFireStation.setAddress("10 test Street");
		mockFireStation.setStation(8);
		FireStation result = fireStationRepository.save(mockFireStation);
		assertThat(mockFireStation, is(result));
		String fileResult = ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "firestations");
		assertThat(fileResult, containsString("\"address\":\"10 test Street\",\"station\":8"));
	}

	@Test
	@Order(3)
	public void DeleteFirestationTest() {
		FireStation mockFireStation = new FireStation();
		mockFireStation.setAddress("10 test Street");
		mockFireStation.setStation(6);
		fireStationRepository.deleteByAddress("10 test Street");
		String fileResult = ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "firestations");
		assertThat(fileResult, not(containsString("\"address\":\"10 test Street\",\"station\":8")));
	}

	@Test
	@Order(4)
	public void GetAllFirestationsTest() {
		Iterable<FireStation> fireStations = fireStationRepository.findAll();
		int count = 0;
		for (FireStation element : fireStations) {
			count++;
		}
		assertThat(count, is(equalTo(13)));
	}

	@Test
	@Order(5)
	public void SaveFirestationFailedTest() {
		FireStation mockFireStation = new FireStation();
		mockFireStation.setAddress("10 test Street");
		mockFireStation.setStation(8);
		assertThat(fireStationRepository.save(mockFireStation), nullValue());
	}
}
