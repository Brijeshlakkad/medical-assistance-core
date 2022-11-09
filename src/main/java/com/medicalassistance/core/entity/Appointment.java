package com.medicalassistance.core.entity;

import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

/**
 * Base class for CounselorAppointment and DoctorAppointment that shares the common features.
 */
public class Appointment extends DateDomainObject {
    @Id
    private String appointmentId;

    private String patientRecordId;

    private ZonedDateTime startDateTime;

    private ZonedDateTime endDateTime;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientRecordId() {
        return patientRecordId;
    }

    public void setPatientRecordId(String patientRecordId) {
        this.patientRecordId = patientRecordId;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
