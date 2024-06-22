package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.util.ArrayList;
import java.util.List;


import com.openclassrooms.P5_SpringBoot_JG.model.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class PersonRepo {
	private Long id;
	
	public ArrayList<Person> persons;
	


	public PersonRepo(ArrayList<Person> persons) {
		this.persons = persons;
		
	}

}
