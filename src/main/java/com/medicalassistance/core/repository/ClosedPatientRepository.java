package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.ActivePatient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClosedPatientRepository extends MongoRepository<ActivePatient, String> {
}