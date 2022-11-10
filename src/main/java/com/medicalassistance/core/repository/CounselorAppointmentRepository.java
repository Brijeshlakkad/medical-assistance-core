package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.CounselorAppointment;
import com.medicalassistance.core.response.AppointmentListForDateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface CounselorAppointmentRepository extends MongoRepository<CounselorAppointment, String> {
    Page<CounselorAppointment> findByCounselorIdAndStartDateTimeGreaterThanEqual(String counselorId, ZonedDateTime date, Pageable pageable);

    List<AppointmentListForDateResponse> findByCounselorIdAndStartDateTimeBetween(String counselorId, ZonedDateTime startDate, ZonedDateTime endDate);

    boolean existsByStartDateTimeBetweenOrStartDateTimeEqualsOrStartDateTimeEquals(ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime startDateTimeE, ZonedDateTime endDateTimeE);

    boolean existsByEndDateTimeBetweenOrEndDateTimeEqualsOrEndDateTimeEquals(ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime startDateTimeE, ZonedDateTime endDateTimeE);

    CounselorAppointment findByAppointmentId(String appointmentId);
}