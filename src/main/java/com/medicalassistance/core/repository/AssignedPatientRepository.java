package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.AssignedPatient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssignedPatientRepository extends MongoRepository<AssignedPatient, String> {
}