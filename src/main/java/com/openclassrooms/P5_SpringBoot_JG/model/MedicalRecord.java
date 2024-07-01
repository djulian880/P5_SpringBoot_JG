package com.openclassrooms.P5_SpringBoot_JG.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class MedicalRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public String firstName;
	public String lastName;
	public Date birthdate;
	public List<String> medications;
	public List<String> allergies;



}
