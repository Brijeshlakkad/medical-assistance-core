package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.ActivePatient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivePatientRepository extends MongoRepository<ActivePatient, String> {
    List<ActivePatient> findAll();

    ActivePatient findByActivePatientId(String activePatientId);

    boolean existsByPatientRecord_PatientId(String patientId);
}