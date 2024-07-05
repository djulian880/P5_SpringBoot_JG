package com.openclassrooms.P5_SpringBoot_JG.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.P5_SpringBoot_JG.service.SafetyNetService;

@RestController
public class SafetyNetController {

	@Autowired
	private SafetyNetService safetyNetService;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	/**
	 * /firestation?stationNumber=<station_number> search by station number
	 * 
	 * @param stationNumber
	 * @return list of persons with firstName, lastName, address, phone. And a list
	 *         of number of children and adults
	 */

	@GetMapping("/firestation")
	ObjectNode returnPersonsByStation(@RequestParam(name = "stationNumber", required = true) Integer stationNumber) {
		logger.debug("Request of data by firestationNumber :" + stationNumber);
		return safetyNetService.returnPersonsByStation(stationNumber);
	}

	/**
	 * /childAlert?address=<address> search for children by address
	 * 
	 * @param address
	 * @return list of persons with firstName, lastName, address, age and list of
	 *         persons living a the same address.
	 */
	@GetMapping("/childAlert")
	ObjectNode returnChildrenByAddress(@RequestParam(name = "address", required = true) String address) {
		logger.debug("Request of data for child by address :" + address);
		return safetyNetService.returnChildrenByAddress(address);
	}

	/**
	 * /phoneAlert?firestation=<firestation_number> search for phone numbers by
	 * firestation numbers
	 * 
	 * @param firestation_number
	 * @return list of phone numbers of persons linked to firestation.
	 */
	@GetMapping("/phoneAlert")
	ObjectNode returnPhonesByStation(@RequestParam(name = "firestation", required = true) Integer stationNumber) {
		logger.debug("Request of data for phone number by firestationNumber :" + stationNumber);
		return safetyNetService.returnPhonesByStation(stationNumber);
	}

	/**
	 * /fire?address=<address> search for persons by address
	 * 
	 * @param address
	 * @return list of persons with last name, phone, age, medical records
	 *         (allergies and medications) and station number
	 */
	@GetMapping("/fire")
	ObjectNode returnPersonsAndStationByAddress(@RequestParam(name = "address", required = true) String address) {
		logger.debug("Request of data for persons by address :" + address);
		return safetyNetService.returnPersonsAndStationByAddress(address);
	}

	/**
	 * flood/stations?stations=<a list of station_numbers> search for persons by
	 * firestation numbers
	 * 
	 * @param a list of station_numbers
	 * @return list of persons regrouped by address with last name, phone, age, and
	 *         medical records
	 */
	@GetMapping("/flood/stations")
	ObjectNode returnHomesAndPersonsMedicalRecordsByStationNumber(
			@RequestParam(name = "stations", required = true) List<Integer> stationNumbers) {
		logger.debug("Request of data for address by stationNumbers :" + stationNumbers);
		return safetyNetService.returnHomesAndPersonsMedicalRecordsByStationNumber(stationNumbers);
	}

	/**
	 * personInfolastName=<lastName> search for persons by last name
	 * 
	 * @param lastName
	 * @return list of persons with last name, address, age, email and medical
	 *         records
	 */

	@GetMapping("/")
	ObjectNode returnPersonsByLastName(@RequestParam(name = "personInfolastName", required = true) String lastName) {
		logger.debug("Request of data for address by lastName :" + lastName);
		return safetyNetService.returnPersonsByLastName(lastName);
	}

	/**
	 * /communityEmail?city=<city> search for emails by city
	 * 
	 * @param city
	 * @return list of emails
	 */

	@GetMapping("/communityEmail")
	ObjectNode returnCommunityEmails(@RequestParam(name = "city", required = true) String city) {
		logger.debug("Request of data for mail by city :" + city);
		return safetyNetService.returnCommunityEmails(city);
	}

}
