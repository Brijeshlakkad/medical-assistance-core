package com.medicalassistance.core.response;

import java.time.ZonedDateTime;

public class PatientRecordCardResponse {
    String patientRecordId;
    PatientCardResponse patient;
    ZonedDateTime assessmentCreatedAt;

    public String getPatientRecordId() {
        return patientRecordId;
    }

    public void setPatientRecordId(String patientRecordId) {
        this.patientRecordId = patientRecordId;
    }

    public PatientCardResponse getPatient() {
        return patient;
    }

    public void setPatient(PatientCardResponse patient) {
        this.patient = patient;
    }

    public ZonedDateTime getAssessmentCreatedAt() {
        return assessmentCreatedAt;
    }

    public void setAssessmentCreatedAt(ZonedDateTime assessmentCreatedAt) {
        this.assessmentCreatedAt = assessmentCreatedAt;
    }
}
