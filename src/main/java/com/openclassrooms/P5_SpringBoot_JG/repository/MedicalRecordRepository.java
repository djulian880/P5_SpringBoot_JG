package com.openclassrooms.P5_SpringBoot_JG.repository;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class MedicalRecordRepository {

	private Long id;

public ArrayList<MedicalRecord> medicalRecords;
	


	public MedicalRecordRepository(ArrayList<MedicalRecord> MedicalRecords) {
		this.medicalRecords = MedicalRecords;
		
	}

	public MedicalRecordRepository() {
		this.medicalRecords = new ArrayList<MedicalRecord>();
		
	}
}
