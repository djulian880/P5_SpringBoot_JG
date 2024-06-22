package com.openclassrooms.P5_SpringBoot_JG.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.repository.MedicalRecordRepository;
import lombok.Data;

@Data
@Service
public class MedicalRecordService {
	  @Autowired
	    private MedicalRecordRepository medicalRecordRepository;

	    public Optional<MedicalRecord> getMedicalRecord(final Long id) {
	        return medicalRecordRepository.findById(id);
	    }

	    public Iterable<MedicalRecord> getMedicalRecords() {
	        return medicalRecordRepository.findAll();
	    }

	    public void deleteMedicalRecord(final Long id) {
	    	medicalRecordRepository.deleteById(id);
	    }

	    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
	    	MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
	        return savedMedicalRecord;
	    }
}
