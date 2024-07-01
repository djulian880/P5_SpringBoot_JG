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
import com.openclassrooms.P5_SpringBoot_JG.Util.ManageRepositoriesFromFile;
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
		
		private ObjectMapper objectMapper = new ObjectMapper();
		
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
				logger.trace("suppression caserne de pompier :" + fireStationToDelete.getAddress());
				fireStations.remove(fireStationToDelete);
				saveToJson();
			} else {
				logger.error("caserne de pompier non trouvée: " + address);
			}

		}

		public FireStation add(FireStation fireStation) {
			readFromJson();
			logger.trace("ajout caserne de pompier :" + fireStation.getAddress()+" "+fireStation.getStation());
			fireStations.add(fireStation);
			saveToJson();
			return fireStations.get(fireStations.lastIndexOf(fireStation));
		}

		public FireStation save(FireStation fireStation) {
			logger.trace("Modification caserne de pompier :" + fireStation.getAddress()+" "+fireStation.getStation());
			readFromJson();
			for (FireStation fireStationEnCours : fireStations) {
				if (fireStationEnCours.getAddress().equals(fireStation.getAddress()) ) {
					fireStationEnCours.setStation(fireStation.getStation());
					saveToJson();

					logger.trace("caserne de pompier trouvée :" + fireStationEnCours.getAddress());
					return fireStationEnCours;

				}
			}

			logger.trace("caserne de pompier pas trouvée :" + fireStation.getAddress());

			return null;

		}

		private void saveToJson() {

			JsonNode fileContent;

			try {
				fileContent = objectMapper.readTree(ManageRepositoriesFromFile.returnContentOfFileAsString(path));
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
				fireStations = objectMapper.readValue(content, new TypeReference<ArrayList<FireStation>>() {
				});
			} catch (JsonMappingException e) {
				logger.error(e.toString());
			} catch (JsonProcessingException e) {
				logger.error(e.toString());
			}
		}

		public void readFromJson() {
			readFireStationsFromJson(ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(
					ManageRepositoriesFromFile.returnContentOfFileAsString(path), "firestations"));
		}
	
}
