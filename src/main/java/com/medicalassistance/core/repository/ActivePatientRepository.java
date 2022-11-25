package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.ActivePatient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivePatientRepository extends MongoRepository<ActivePatient, String> {
    Page<ActivePatient> findAll(Pageable pageable);

    ActivePatient findByActivePatientId(String activePatientId);

    ActivePatient findByPatientId(String patientId);

    boolean existsByActivePatientId(String activePatientId);

    boolean existsByPatientId(String patientId);

    void deleteByActivePatientId(String activePatientId);
}