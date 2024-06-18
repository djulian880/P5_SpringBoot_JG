package com.openclassrooms.P5_SpringBoot_JG.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class Person {

	private Long id;
	
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public int zip;
	public String phone;
	public String email;

	public Person(String firstName, String lastName, String address, String city, int zip, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public Person() {
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.city = "";
		this.zip = 0;
		this.phone = "";
		this.email = "";
	}

}
