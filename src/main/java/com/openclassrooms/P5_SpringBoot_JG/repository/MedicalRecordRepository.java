package com.openclassrooms.P5_SpringBoot_JG.repository;

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

private List<MedicalRecord> MedicalRecords;
	


	public MedicalRecordRepository(List<MedicalRecord> MedicalRecords) {
		this.MedicalRecords = MedicalRecords;
		
	}


}
