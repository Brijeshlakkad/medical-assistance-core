package com.medicalassistance.core.response;

import java.util.Date;

public class PatientRecordCardResponse {
    String patientRecordId;
    UserCardResponse patientResponse;
    Date assessmentCreatedAt;

    public String getPatientRecordId() {
        return patientRecordId;
    }

    public void setPatientRecordId(String patientRecordId) {
        this.patientRecordId = patientRecordId;
    }

    public UserCardResponse getPatientResponse() {
        return patientResponse;
    }

    public void setPatientResponse(UserCardResponse patientResponse) {
        this.patientResponse = patientResponse;
    }

    public Date getAssessmentCreatedAt() {
        return assessmentCreatedAt;
    }

    public void setAssessmentCreatedAt(Date assessmentCreatedAt) {
        this.assessmentCreatedAt = assessmentCreatedAt;
    }
}
