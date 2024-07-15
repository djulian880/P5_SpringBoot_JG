package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.P5_SpringBoot_JG.Util.JsonTools;
import com.openclassrooms.P5_SpringBoot_JG.Util.ManageJsonFile;
import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Repository
public class FireStationRepository {
	@Autowired

	private static Logger logger = LoggerFactory.getLogger(FireStationRepository.class);

	private static String path = "src/main/resources/data.json";

	public ArrayList<FireStation> fireStations;

	public FireStation findByAddress(String address) {
		readFromJson();
		for (FireStation fireStation : fireStations) {
			if (fireStation.getAddress().equals(address)) {
				return fireStation;
			}
		}
		return null;
	}

	public Iterable<FireStation> findAll() {
		readFromJson();
		return fireStations;
	}

	public void deleteByAddress(String address) {
		Optional<FireStation> fireStation = Optional.ofNullable(findByAddress(address));
		if (fireStation.isPresent()) {
			FireStation fireStationToDelete = fireStation.get();
			logger.debug("deletion of firestation :" + fireStationToDelete.getAddress());
			fireStations.remove(fireStationToDelete);
			saveToJson();
		} else {
			logger.error("firestation not found at: " + address);
		}

	}

	public FireStation add(FireStation fireStation) {
		readFromJson();
		logger.debug("creation of firesation :" + fireStation.getAddress() + " " + fireStation.getStation());
		fireStations.add(fireStation);
		saveToJson();
		return fireStations.get(fireStations.lastIndexOf(fireStation));
	}

	public FireStation save(FireStation fireStation) {
		logger.debug("Update of firestation :" + fireStation.getAddress() + " " + fireStation.getStation());
		readFromJson();
		for (FireStation fireStationEnCours : fireStations) {
			if (fireStationEnCours.getAddress().equals(fireStation.getAddress())) {
				fireStationEnCours.setStation(fireStation.getStation());
				saveToJson();

				logger.debug("Firestation found :" + fireStationEnCours.getAddress());
				return fireStationEnCours;

			}
		}
		logger.error("Firestation not found :" + fireStation.getAddress());
		return null;
	}

	private void saveToJson() {
		try {
			 ObjectMapper objectMapper = JsonTools.getObjectMapper();
			JsonNode fileContent = objectMapper.readTree(ManageJsonFile.returnContentOfFileAsString(path));
			String contenu = objectMapper.writeValueAsString(fireStations);
			JsonNode fireStationsAsJsonNode = objectMapper.readTree(contenu);
			((ObjectNode) fileContent).set("firestations", fireStationsAsJsonNode);
			FileWriter file = new FileWriter(path);
			objectMapper.writeValue(file, fileContent);

		} catch (JsonMappingException e) {
			logger.error(e.toString());
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}

	}

	public void readFireStationsFromJson(String content) {
		try {
			ObjectMapper objectMapper = JsonTools.getObjectMapper();
			fireStations = objectMapper.readValue(content, new TypeReference<ArrayList<FireStation>>() {
			});
		} catch (JsonMappingException e) {
			logger.error(e.toString());
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		}
	}

	public void readFromJson() {
		readFireStationsFromJson(ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "firestations"));
	}

}
