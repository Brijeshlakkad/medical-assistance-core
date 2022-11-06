package com.medicalassistance.core.response;

import java.time.ZonedDateTime;

public class AppointmentResponse {
    private String appointmentId;

    private UserCardResponse patient;

    private ZonedDateTime startDateTime;

    private ZonedDateTime endDateTime;

    private ZonedDateTime createdAt;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public UserCardResponse getPatient() {
        return patient;
    }

    public void setPatient(UserCardResponse patient) {
        this.patient = patient;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
