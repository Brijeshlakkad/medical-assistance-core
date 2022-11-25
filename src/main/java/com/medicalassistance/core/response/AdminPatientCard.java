package com.medicalassistance.core.response;

import java.time.ZonedDateTime;

public class AdminPatientCard {
    UserCardResponse patient;
    ZonedDateTime assessmentCreatedAt;

    public UserCardResponse getPatient() {
        return patient;
    }

    public void setPatient(UserCardResponse patient) {
        this.patient = patient;
    }

    public ZonedDateTime getAssessmentCreatedAt() {
        return assessmentCreatedAt;
    }

    public void setAssessmentCreatedAt(ZonedDateTime assessmentCreatedAt) {
        this.assessmentCreatedAt = assessmentCreatedAt;
    }
}