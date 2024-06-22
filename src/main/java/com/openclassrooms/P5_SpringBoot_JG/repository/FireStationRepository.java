package com.openclassrooms.P5_SpringBoot_JG.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;

@Repository
public interface FireStationRepository extends CrudRepository<FireStation, Long>{

}
