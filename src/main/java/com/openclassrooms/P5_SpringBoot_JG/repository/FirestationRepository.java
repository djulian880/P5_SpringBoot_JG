package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor // <--- THIS is it
public class FirestationRepository {

	private Long id;
	
	 public ArrayList<FireStation> firestations;
	


	public FirestationRepository(ArrayList<FireStation> firestations) {
		this.firestations = firestations;
		
	}
	
	public FirestationRepository() {
		this.firestations=new ArrayList<FireStation>();
		

	}
	
}
