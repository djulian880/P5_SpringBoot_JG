package com.openclassrooms.P5_SpringBoot_JG.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String FirstName;
	private String LastName;
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;



}
