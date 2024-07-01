package com.openclassrooms.P5_SpringBoot_JG.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	private static Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

	/**
	 * Read - Get all medicalRecords
	 * 
	 * @return - An Iterable object of MedicalRecord full filled
	 */
	@GetMapping("/medicalRecords")
	public Iterable<MedicalRecord> getMedicalRecords() {
		return medicalRecordService.getMedicalRecords();
	}

	/**
	 * Create - Add a new MedicalRecord
	 * 
	 * @param MedicalRecord the object MedicalRecord to be created
	 * @return The MedicalRecord object saved
	 */
	@PostMapping("/medicalRecord")
	@ResponseStatus(HttpStatus.OK)
	public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord MedicalRecord) {
		logger.trace("Controller: écriture medicalRecord :" + MedicalRecord.getFirstName());

		Optional<MedicalRecord> e = Optional.ofNullable(medicalRecordService.getMedicalRecord(MedicalRecord.getFirstName(), MedicalRecord.getLastName()));
		if (e.isEmpty()) {
			return medicalRecordService.addMedicalRecord(MedicalRecord);
		} else {
			logger.error(
					"Cette medicalRecord existe déjà dans la BDD:" + MedicalRecord.getFirstName() + " " + MedicalRecord.getLastName());
			throw new MedicalRecordAlreadyExistsException();
		}
	}

	/**
	 * Update - Update an existing MedicalRecord
	 * 
	 * @param MedicalRecord - The MedicalRecord object updated
	 * @return MedicalRecord - The MedicalRecord object updated
	 */
	@PutMapping("/medicalRecord")
	@ResponseStatus(HttpStatus.OK)
	public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord MedicalRecord) {
		logger.trace("Controller: update medicalRecordne :" + MedicalRecord.getFirstName());
		Optional<MedicalRecord> medicalRecordFound = Optional
				.ofNullable(medicalRecordService.getMedicalRecord(MedicalRecord.getFirstName(), MedicalRecord.getLastName()));
		if (medicalRecordFound.isPresent()) {
			MedicalRecord currentMedicalRecord = medicalRecordFound.get();
			logger.trace("Mise à jour medicalRecordne trouvée :" + currentMedicalRecord.getFirstName());
			Date birthDate = MedicalRecord.getBirthdate();
			if (birthDate != null) {
				currentMedicalRecord.setBirthdate(birthDate);
			}
			List<String> medications=MedicalRecord.getMedications();
			if (medications != null) {
				currentMedicalRecord.setMedications(medications);
			}
			List<String> allergies = MedicalRecord.getAllergies();
			if (allergies != null) {
				currentMedicalRecord.setAllergies(allergies);
			}
			

			return medicalRecordService.saveMedicalRecord(currentMedicalRecord);
		} else {

			logger.error("MedicalRecordne non touvée dans la BDD:" + MedicalRecord.getFirstName() + " " + MedicalRecord.getLastName());
			throw new MedicalRecordNotFoundException();

		}
	}

	/**
	 * Delete - Delete an MedicalRecord
	 * 
	 * @param MedicalRecord - The MedicalRecord object updated
	 * 
	 */
	@DeleteMapping("/medicalRecord")
	@ResponseStatus(HttpStatus.OK)
	public void deleteMedicalRecord(@RequestBody MedicalRecord MedicalRecord) {
		Optional<MedicalRecord> medicalRecordFound = Optional
				.ofNullable(medicalRecordService.getMedicalRecord(MedicalRecord.getFirstName(), MedicalRecord.getLastName()));
		if (medicalRecordFound.isPresent()) {
			logger.trace("Suppression medicalRecordne :" + MedicalRecord.getFirstName() + " " + MedicalRecord.getLastName());
			medicalRecordService.deleteMedicalRecord(MedicalRecord.getFirstName(), MedicalRecord.getLastName());
		} else {
			logger.error("MedicalRecordne non touvée dans la BDD:" + MedicalRecord.getFirstName() + " " + MedicalRecord.getLastName());
			throw new MedicalRecordNotFoundException();
		}

	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	public static class MedicalRecordAlreadyExistsException extends RuntimeException {
		private static final long serialVersionUID = 1239216111432570194L;

		public MedicalRecordAlreadyExistsException() {
			super("MedicalRecord already exists");
		}
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class MedicalRecordNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1239216111432570194L;

		public MedicalRecordNotFoundException() {
			super("MedicalRecord not found");
		}
	}

}
