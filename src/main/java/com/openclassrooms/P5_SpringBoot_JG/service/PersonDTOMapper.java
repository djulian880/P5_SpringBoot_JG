package com.openclassrooms.P5_SpringBoot_JG.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.model.PersonDTO;
import com.openclassrooms.P5_SpringBoot_JG.repository.PersonRepository;


@Service
public class PersonDTOMapper {
	
	@Autowired
	private PersonService personService;
	@Autowired
	private FireStationService fireStationService;
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	private static Logger logger = LoggerFactory.getLogger(PersonRepository.class);
	
	public List<PersonDTO> getAllPersons(){
		
		ArrayList<PersonDTO> personsDto = new ArrayList<>();
		
		
		for (Person person : personService.getPersons()) {
			PersonDTO currentPersonDTO=new PersonDTO();
			
			
			currentPersonDTO.setFirstName(person.getFirstName());
			currentPersonDTO.setLastName(person.getLastName());
			currentPersonDTO.setAddress(person.getAddress());
			currentPersonDTO.setCity(person.getCity());
			currentPersonDTO.setZip(person.getZip());
			currentPersonDTO.setPhone(person.getPhone());
			currentPersonDTO.setEmail(person.getEmail());
			
			FireStation fireStation=fireStationService.getFireStation(person.getAddress());
			if(fireStation!=null) {
				currentPersonDTO.setStation(fireStation.getStation());
			}
			else {
				currentPersonDTO.setStation(0);
			}
			
			
			MedicalRecord medicalRecord=medicalRecordService.getMedicalRecord(person.getFirstName(), person.getLastName());
			if(medicalRecord==null) {
			
				medicalRecord=new MedicalRecord();
			
			}
			currentPersonDTO.setBirthdate(medicalRecord.getBirthdate());
			currentPersonDTO.setMedications(medicalRecord.getMedications());
			currentPersonDTO.setAllergies(medicalRecord.getAllergies());
			
			personsDto.add(currentPersonDTO);

		}
		
		
		return personsDto;
	}
}
