package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Repository
public class MedicalRecordRepository {

	public ArrayList<MedicalRecord> medicalRecords;

	@Autowired

	private static Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	private static String path = "src/main/resources/data.json";

	public MedicalRecord findByFirstAndLastName(String firstName, String lastName) {
		readFromJson();
		for (MedicalRecord medrec : medicalRecords) {
			if (medrec.getFirstName().equals(firstName) && medrec.getLastName().equals(lastName)) {
				return medrec;
			}
		}
		return null;
	}

	public Iterable<MedicalRecord> findAll() {
		readFromJson();
		return medicalRecords;
	}

	public void deleteByFirstAndLastName(String firstName, String lastName) {
		Optional<MedicalRecord> medicalRecord = Optional.ofNullable(findByFirstAndLastName(firstName, lastName));
		if (medicalRecord.isPresent()) {
			MedicalRecord medicalRecordToDelete = medicalRecord.get();
			logger.debug("deletion of medicalrecord :" + medicalRecordToDelete.getFirstName());
			medicalRecords.remove(medicalRecordToDelete);
			saveToJson();
		} else {
			logger.error("Medical record not found: " + firstName + " " + lastName);
		}

	}

	public MedicalRecord add(MedicalRecord medicalRecord) {
		readFromJson();
		logger.debug("Creation of medical record :" + medicalRecord.getFirstName());
		medicalRecords.add(medicalRecord);
		saveToJson();
		return medicalRecords.get(medicalRecords.lastIndexOf(medicalRecord));
	}

	public MedicalRecord save(MedicalRecord medicalRecord) {
		logger.debug("Update of medical record  :" + medicalRecord.getFirstName());
		readFromJson();
		for (MedicalRecord medRed : medicalRecords) {
			if (medRed.getFirstName().equals(medicalRecord.getFirstName())
					&& medRed.getLastName().equals(medicalRecord.getLastName())) {
				medRed.setBirthdate(medicalRecord.getBirthdate());
				medRed.setMedications(medicalRecord.getMedications());
				medRed.setAllergies(medicalRecord.getAllergies());
				saveToJson();
				logger.debug("medical record found :" + medRed.getFirstName());
				return medRed;
			}
		}
		logger.error("Medical record not found :" + medicalRecord.getFirstName());
		return null;
	}

	private void saveToJson() {
		ObjectMapper objectMapper = JsonTools.getObjectMapper();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		objectMapper.setDateFormat(df);

		try {
			JsonNode fileContent = objectMapper.readTree(ManageJsonFile.returnContentOfFileAsString(path));
			String contenu = objectMapper.writeValueAsString(medicalRecords);
			JsonNode medicalRecordsAsJsonNode = objectMapper.readTree(contenu);
			((ObjectNode) fileContent).set("medicalrecords", medicalRecordsAsJsonNode);
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

	public void readMedicalRecordsFromJson(String content) {
		ObjectMapper objectMapper = JsonTools.getObjectMapper();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		objectMapper.setDateFormat(df);
		try {
			medicalRecords = objectMapper.readValue(content, new TypeReference<ArrayList<MedicalRecord>>() {
			});
		} catch (JsonMappingException e) {
			logger.error(e.toString());
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		}
	}

	public void readFromJson() {
		readMedicalRecordsFromJson(ManageJsonFile.returnContentOfJSONNodeAsString(
				ManageJsonFile.returnContentOfFileAsString(path), "medicalrecords"));
	}

}
