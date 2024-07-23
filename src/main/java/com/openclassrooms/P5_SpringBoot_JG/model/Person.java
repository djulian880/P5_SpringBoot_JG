package com.openclassrooms.P5_SpringBoot_JG.model;


import lombok.Data;

@Data
public class Person {
	
	private String FirstName;
	private String LastName;
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;

}
