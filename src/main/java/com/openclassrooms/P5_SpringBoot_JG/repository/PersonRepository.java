package com.openclassrooms.P5_SpringBoot_JG.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.P5_SpringBoot_JG.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
