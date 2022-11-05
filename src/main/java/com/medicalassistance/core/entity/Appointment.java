package com.medicalassistance.core.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Base class for CounselorAppointment and DoctorAppointment that shares the common features.
 */
public class Appointment {
    @Id
    private String appointmentId;

    private String patientId;

    private Date startDateTime;

    private Date endDateTime;

    private Date createdAt;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}