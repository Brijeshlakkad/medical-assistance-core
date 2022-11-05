package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.PatientRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRecordRepository extends MongoRepository<PatientRecord, String> {
    PatientRecord findByPatientRecordId(String patientRecordId);
}