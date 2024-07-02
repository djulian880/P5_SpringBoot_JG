package com.openclassrooms.P5_SpringBoot_JG.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.model.PersonDTO;


@Service
public class PersonDTOMapper {
	
	@Autowired
	private PersonService personService;
	@Autowired
	private FireStationService fireStationService;	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	
	
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
			
			currentPersonDTO.setStation(fireStationService.getFireStation(person.getAddress()).getStation());
			
			MedicalRecord medicalRecord=medicalRecordService.getMedicalRecord(person.getFirstName(), person.getLastName());
			
			currentPersonDTO.setBirthdate(medicalRecord.getBirthdate());
			currentPersonDTO.setMedications(medicalRecord.getMedications());
			currentPersonDTO.setAllergies(medicalRecord.getAllergies());
			
			personsDto.add(currentPersonDTO);

		}
		
		
		return personsDto;
	}
}
