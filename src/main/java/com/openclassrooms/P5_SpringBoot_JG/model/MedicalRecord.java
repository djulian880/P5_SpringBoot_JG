package com.openclassrooms.P5_SpringBoot_JG.model;

import java.util.Date;
import java.util.List;
import java.util.Map;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class MedicalRecord {

	private Long id;

	private String firstName;
	private String lastName;
	private Date birthdate;
	private Map<String, String> medications;
	private List<String> allergies;

	public MedicalRecord(String firstName, String lastName, Date birthdate, Map<String, String> medications,
			List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

}
