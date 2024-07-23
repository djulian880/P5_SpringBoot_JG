package com.openclassrooms.p5springbootjg.DTO;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.p5springbootjg.Util.SafetyNetUtil;
import com.openclassrooms.p5springbootjg.model.FireStation;
import com.openclassrooms.p5springbootjg.model.MedicalRecord;
import com.openclassrooms.p5springbootjg.model.Person;
import com.openclassrooms.p5springbootjg.repository.PersonRepository;
import com.openclassrooms.p5springbootjg.service.FireStationService;
import com.openclassrooms.p5springbootjg.service.MedicalRecordService;
import com.openclassrooms.p5springbootjg.service.PersonService;


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
			currentPersonDTO.setAge(SafetyNetUtil.calculateAge(currentPersonDTO.getBirthdate()));
			personsDto.add(currentPersonDTO);
		}
		return personsDto;
	}
}
