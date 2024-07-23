package com.openclassrooms.p5springbootjg.DTO;

import java.util.Date;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PersonDTO {
	
	public String firstName;
	public String lastName;
	public Date birthdate;
	public List<String> medications;
	public List<String> allergies;
	public String city;
	public int zip;
	public String phone;
	public String email;
	public String address;
	public int station;
	public double age;

}
