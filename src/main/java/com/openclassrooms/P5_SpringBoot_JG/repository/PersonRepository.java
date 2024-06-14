package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.util.List;


import com.openclassrooms.P5_SpringBoot_JG.model.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class PersonRepository {
	private Long id;
	
	private List<Person> persons;
	


	public PersonRepository(List<Person> persons) {
		this.persons = persons;
		
	}
	
}
