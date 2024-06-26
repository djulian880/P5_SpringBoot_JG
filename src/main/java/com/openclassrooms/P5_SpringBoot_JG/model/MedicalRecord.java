package com.openclassrooms.P5_SpringBoot_JG.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

	public String firstName;
	public String lastName;
	public Date birthdate;
	public List<String> medications;
	public List<String> allergies;

	public MedicalRecord(String firstName, String lastName, Date birthdate, ArrayList<String> medications,
			ArrayList<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}
	
	public MedicalRecord() {
		this.firstName = "";
		this.lastName = "";
		this.birthdate = null;
		this.medications = new ArrayList<String> ();
		this.allergies = new ArrayList<String>();
	}


}
