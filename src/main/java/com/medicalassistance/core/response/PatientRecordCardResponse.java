package com.medicalassistance.core.response;

import java.util.Date;

public class PatientRecordCardResponse {
    String patientRecordId;
    UserCardResponse patient;
    Long assessmentCreatedAt;

    public String getPatientRecordId() {
        return patientRecordId;
    }

    public void setPatientRecordId(String patientRecordId) {
        this.patientRecordId = patientRecordId;
    }

    public UserCardResponse getPatient() {
        return patient;
    }

    public void setPatient(UserCardResponse patient) {
        this.patient = patient;
    }

    public Long getAssessmentCreatedAt() {
        return assessmentCreatedAt;
    }

    public void setAssessmentCreatedAt(Date assessmentCreatedAt) {
        this.assessmentCreatedAt = assessmentCreatedAt.getTime();
    }
}
