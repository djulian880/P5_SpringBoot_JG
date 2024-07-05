package com.openclassrooms.P5_SpringBoot_JG.controller;

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

import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import com.openclassrooms.P5_SpringBoot_JG.service.FireStationService;

@RestController
public class FireStationController {
	@Autowired
	private FireStationService fireStationService;

	private static Logger logger = LoggerFactory.getLogger(FireStationController.class);

	/**
	 * Read - Get all fireStations
	 * 
	 * @return - An Iterable object of FireStation full filled
	 */
	@GetMapping("/firestations")
	public Iterable<FireStation> getFireStations() {
		return fireStationService.getFireStations();
	}

	/**
	 * Create - Add a new FireStation
	 * 
	 * @param FireStation the object FireStation to be created
	 * @return The FireStation object saved
	 */
	@PostMapping("/firestation")
	@ResponseStatus(HttpStatus.OK)
	public FireStation createFireStation(@RequestBody FireStation FireStation) {
		logger.debug("Request for création of fireStation :" + FireStation.toString());
		Optional<FireStation> e = Optional.ofNullable(fireStationService.getFireStation(FireStation.getAddress()));

		if (e.isEmpty()) {
			logger.info("Successful création of firestation " + FireStation.toString());
			return fireStationService.addFireStation(FireStation);
		} else {
			logger.error("This firestation already exists :" + FireStation.getAddress());
			throw new FireStationAlreadyExistsException();
		}
	}

	/**
	 * Update - Update an existing FireStation
	 * 
	 * @param FireStation - The FireStation object updated
	 * @return FireStation - The FireStation object updated
	 */
	@PutMapping("/firestation")
	@ResponseStatus(HttpStatus.OK)
	public FireStation updateFireStation(@RequestBody FireStation FireStation) {
		logger.debug("Request of update of fireStation :" + FireStation.toString());
		Optional<FireStation> fireStationFound = Optional
				.ofNullable(fireStationService.getFireStation(FireStation.getAddress()));

		if (fireStationFound.isPresent()) {
			FireStation currentFireStation = fireStationFound.get();
			logger.debug("Update of foun firestation :" + currentFireStation.getAddress());
			Integer station = FireStation.getStation();
			if (station != 0) {
				logger.debug("Successful update of firestation :" + currentFireStation.getAddress());
				currentFireStation.setStation(station);
			}

			return fireStationService.saveFireStation(currentFireStation);

		} else {

			logger.error("firestation not found: " + FireStation.toString());
			throw new FireStationNotFoundException();

		}
	}

	/**
	 * Delete - Delete an FireStation
	 * 
	 * @param FireStation - The FireStation object updated
	 * 
	 */
	@DeleteMapping("/firestation")
	@ResponseStatus(HttpStatus.OK)
	public void deleteFireStation(@RequestBody FireStation FireStation) {
		logger.debug("Request for deletion of fireStation :" + FireStation.toString());
		Optional<FireStation> fireStationFound = Optional
				.ofNullable(fireStationService.getFireStation(FireStation.getAddress()));
		if (fireStationFound.isPresent()) {
			fireStationService.deleteFireStation(FireStation.getAddress());
			logger.info("Successful deletion of firestation :" + FireStation.getAddress());
		} else {
			logger.error("firestation not found:" + FireStation.toString());
			throw new FireStationNotFoundException();
		}

	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	public static class FireStationAlreadyExistsException extends RuntimeException {
		private static final long serialVersionUID = 1239216111432570194L;

		public FireStationAlreadyExistsException() {
			super("FireStation already exists");
		}
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class FireStationNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1239216111432570194L;

		public FireStationNotFoundException() {
			super("FireStation not found");
		}
	}
}
