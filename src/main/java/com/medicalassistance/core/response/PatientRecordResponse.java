package com.medicalassistance.core.response;

import java.util.Date;

public class PatientRecordResponse {
    String recordId;
    UserCardResponse patient;
    Date createdAt;
    AssessmentResultResponse assessmentResult;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public UserCardResponse getPatient() {
        return patient;
    }

    public void setPatient(UserCardResponse patient) {
        this.patient = patient;
    }

    public AssessmentResultResponse getAssessmentResult() {
        return assessmentResult;
    }

    public void setAssessmentResult(AssessmentResultResponse assessmentResult) {
        this.assessmentResult = assessmentResult;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
