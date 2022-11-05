package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.CounselorAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface CounselorAppointmentRepository extends MongoRepository<CounselorAppointment, String> {
    Page<CounselorAppointment> findByCounselorIdAndStartDateTimeGreaterThanEqual(String doctorId, Date date, Pageable pageable);

    boolean existsByStartDateTimeBetweenOrStartDateTimeEqualsOrStartDateTimeEquals(Date startDateTime, Date endDateTime, Date startDateTimeE, Date endDateTimeE);

    boolean existsByEndDateTimeBetweenOrEndDateTimeEqualsOrEndDateTimeEquals(Date startDateTime, Date endDateTime, Date startDateTimeE, Date endDateTimeE);
}