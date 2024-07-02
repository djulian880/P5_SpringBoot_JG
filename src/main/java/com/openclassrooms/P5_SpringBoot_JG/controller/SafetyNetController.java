package com.openclassrooms.P5_SpringBoot_JG.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.model.PersonDTO;
import com.openclassrooms.P5_SpringBoot_JG.service.PersonDTOMapper;

@RestController
public class SafetyNetController {

	@Autowired
	private PersonDTOMapper personDTOMapper;
	
	private static Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	

	// http://localhost:8080/firestation?stationNumber=<station_number>
//		Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers
//		correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les habitants
//		couverts par la station numéro 1. La liste doit inclure les informations spécifiques
//		suivantes : prénom, nom, adresse, numéro de téléphone. De plus, elle doit fournir un
//		décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
//		moins) dans la zone desservie.
	@RequestMapping("/firestation")
	List<String> returnPersonsBySation(@RequestParam(name = "stationNumber", required = true) Integer stationNumber) {

		return null;
	}

//		http://localhost:8080/childAlert?address=<address>
//		Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins)
//		habitant à cette adresse. La liste doit comprendre le prénom et le nom de famille de
//		chaque enfant, son âge et une liste des autres membres du foyer. S'il n'y a pas
//		d'enfant, cette url peut renvoyer une chaîne vide.
	@RequestMapping("/childAlert")
	List<String> returnChildsByAddress(@RequestParam(name = "address", required = true) String address) {

		return null;
	}

//		http://localhost:8080/phoneAlert?firestation=<firestation_number>
//		Cette url doit retourner une liste des numéros de téléphone des résidents desservis
//		par la caserne de pompiers. Nous l'utiliserons pour envoyer des messages texte
//		d'urgence à des foyers spécifiques.
	@RequestMapping("/phoneAlert")
	List<String> returnPhonesByStation(
			@RequestParam(name = "firestation_number", required = true) Integer stationNumber) {

		return null;
	}

//		http://localhost:8080/fire?address=<address>
//		Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le
//		numéro de la caserne de pompiers la desservant. La liste doit inclure le nom, le
//		numéro de téléphone, l'âge et les antécédents médicaux (médicaments, posologie et
//		allergies) de chaque personne.
	@RequestMapping("/fire")
	List<String> returnPersonsAndStationByAddress(@RequestParam(name = "address", required = true) String address) {

		return null;
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	// Cette url doit retourner une liste de tous les foyers desservis par la
	// caserne. Cette
	// liste doit regrouper les personnes par adresse. Elle doit aussi inclure le
	// nom, le
	// numéro de téléphone et l'âge des habitants, et faire figurer leurs
	// antécédents
	// médicaux (médicaments, posologie et allergies) à côté de chaque nom.
	@RequestMapping("/flood/stations")
	List<String> returnHomesAndPersonsMedicalRecordsByStationNumber(
			@RequestParam(name = "stations", required = true) List<Integer> stationNumbers) {

		return null;
	}

	// http://localhost:8080/personInfolastName=<lastName>
	// Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les
	// antécédents
	// médicaux (médicaments, posologie et allergies) de chaque habitant. Si
	// plusieurs
	// personnes portent le même nom, elles doivent toutes apparaître.

	@RequestMapping("/")
	List<String> returnPersonsByLastName(@RequestParam(name = "personInfolastName", required = true) String lastName) {

		return null;
	}

	// Cette url doit retourner les adresses mail de tous les habitants de la ville.
	@RequestMapping("/communityEmail")
	List<String> returnCommunityEmails(@RequestParam(name = "city", required = true) String city) {
		
		ArrayList<String> result=new ArrayList<>();
		
		
		for (PersonDTO personDTO : personDTOMapper.getAllPersons()) {
			
			if(personDTO.getCity().equals(city)) {
				result.add(personDTO.getEmail());
			}


		}
		
		return result;
	}

}
