package com.openclassrooms.P5_SpringBoot_JG.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Entity
@Table(name = "persons")
public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter	private String FirstName;
	private String LastName;
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;
/*
	public Person(String firstName, String lastName, String address, String city, int zip, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	/*
	public Person() {
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.city = "";
		this.zip = 0;
		this.phone = "";
		this.email = "";
	}*/


}
