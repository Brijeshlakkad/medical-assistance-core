package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.CounselorAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface CounselorAppointmentRepository extends MongoRepository<CounselorAppointment, String> {
    Page<CounselorAppointment> findByCounselorIdAndAppointmentDateGreaterThanEqual(String doctorId, Date date, Pageable pageable);
}