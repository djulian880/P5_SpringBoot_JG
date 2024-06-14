package com.openclassrooms.P5_SpringBoot_JG.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class Person {

	private Long id;
	
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;

	public Person(String firstName, String lastName, String address, String city, int zip, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

}
