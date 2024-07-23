package com.openclassrooms.P5_SpringBoot_JG.model;

import java.util.Date;
import java.util.List;
import lombok.Data;


@Data
public class MedicalRecord {

	public String firstName;
	public String lastName;
	public Date birthdate;
	public List<String> medications;
	public List<String> allergies;



}
