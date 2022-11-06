package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.DoctorAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;

public interface DoctorAppointmentRepository extends MongoRepository<DoctorAppointment, String> {
    Page<DoctorAppointment> findByDoctorIdAndStartDateTimeGreaterThanEqual(String doctorId, ZonedDateTime date, Pageable pageable);

    boolean existsByStartDateTimeBetweenOrStartDateTimeEqualsOrStartDateTimeEquals(ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime startDateTimeE, ZonedDateTime endDateTimeE);

    boolean existsByEndDateTimeBetweenOrEndDateTimeEqualsOrEndDateTimeEquals(ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime startDateTimeE, ZonedDateTime endDateTimeE);

    DoctorAppointment findByAppointmentId(String appointmentId);
}