package com.medicalassistance.core.entity;

/**
 * Represents the patient record.
 */
public class PatientRecord {
    private String assessmentResultId;

    private String patientId;

    public PatientRecord(String assessmentResultId, String patientId) {
        this.assessmentResultId = assessmentResultId;
        this.patientId = patientId;
    }

    public String getAssessmentResultId() {
        return assessmentResultId;
    }

    public void setAssessmentResultId(String assessmentResultId) {
        this.assessmentResultId = assessmentResultId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
