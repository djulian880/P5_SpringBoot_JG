package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.util.List;

import com.openclassrooms.P5_SpringBoot_JG.model.Firestation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class FirestationRepository {

	private Long id;
	
	private List<Firestation> firestations;
	


	public FirestationRepository(List<Firestation> firestations) {
		this.firestations = firestations;
		
	}
	
}
