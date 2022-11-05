package com.medicalassistance.core.entity;

import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

/**
 * Base class for CounselorAppointment and DoctorAppointment that shares the common features.
 */
public class Appointment extends DateDomainObject {
    @Id
    private String appointmentId;

    private String activePatientId;

    private ZonedDateTime startDateTime;

    private ZonedDateTime endDateTime;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getActivePatientId() {
        return activePatientId;
    }

    public void setActivePatientId(String activePatientId) {
        this.activePatientId = activePatientId;
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
