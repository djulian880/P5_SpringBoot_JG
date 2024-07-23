package com.openclassrooms.p5springbootjg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.p5springbootjg.model.FireStation;
import com.openclassrooms.p5springbootjg.repository.FireStationRepository;

import lombok.Data;

@Data
@Service
public class FireStationService {
	
		@Autowired
	    private FireStationRepository fireStationRepository;
	    
		public FireStation getFireStation(String address) {
			return fireStationRepository.findByAddress(address);
		}

		public Iterable<FireStation> getFireStations() {
			return fireStationRepository.findAll();
		}

		public void deleteFireStation(String address) {
			fireStationRepository.deleteByAddress(address);
		}

		public FireStation saveFireStation(FireStation fireStation) {
			FireStation savedFireStation = fireStationRepository.save(fireStation);
			return savedFireStation;
		}

		public FireStation addFireStation(FireStation fireStation) {
			FireStation newFireStation = fireStationRepository.add(fireStation);
			return newFireStation;
		}
}
