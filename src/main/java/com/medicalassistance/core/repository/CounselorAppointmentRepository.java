package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.CounselorAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;

public interface CounselorAppointmentRepository extends MongoRepository<CounselorAppointment, String> {
    Page<CounselorAppointment> findByCounselorIdAndStartDateTimeGreaterThanEqual(String doctorId, ZonedDateTime date, Pageable pageable);

    boolean existsByStartDateTimeBetweenOrStartDateTimeEqualsOrStartDateTimeEquals(ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime startDateTimeE, ZonedDateTime endDateTimeE);

    boolean existsByEndDateTimeBetweenOrEndDateTimeEqualsOrEndDateTimeEquals(ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime startDateTimeE, ZonedDateTime endDateTimeE);

    CounselorAppointment findByAppointmentId(String appointmentId);
}