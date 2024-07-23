package com.openclassrooms.p5springbootjg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.p5springbootjg.model.MedicalRecord;
import com.openclassrooms.p5springbootjg.repository.MedicalRecordRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public MedicalRecord getMedicalRecord(String firstName, String lastName) {
		return medicalRecordRepository.findByFirstAndLastName(firstName, lastName);
	}

	public Iterable<MedicalRecord> getMedicalRecords() {
		return medicalRecordRepository.findAll();
	}

	public void deleteMedicalRecord(String firstName, String lastName) {
		medicalRecordRepository.deleteByFirstAndLastName(firstName, lastName);
	}

	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
		return savedMedicalRecord;
	}

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord newMedicalRecord = medicalRecordRepository.add(medicalRecord);
		return newMedicalRecord;
	}
}
