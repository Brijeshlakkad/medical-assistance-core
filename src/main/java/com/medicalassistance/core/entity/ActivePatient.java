package com.medicalassistance.core.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Contains patients that has completed their assessment.
 */
@Document("active_patients")
public class ActivePatient extends DateDomainObject {
    @Id
    private String activePatientId;

    @Indexed(unique = true)
    private String patientId;

    private String assessmentResultId;

    @Indexed(unique = true)
    private String patientRecordId;

    public String getActivePatientId() {
        return activePatientId;
    }

    public void setActivePatientId(String activePatientId) {
        this.activePatientId = activePatientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAssessmentResultId() {
        return assessmentResultId;
    }

    public void setAssessmentResultId(String assessmentResultId) {
        this.assessmentResultId = assessmentResultId;
    }

    public String getPatientRecordId() {
        return patientRecordId;
    }

    public void setPatientRecordId(String patientRecordId) {
        this.patientRecordId = patientRecordId;
    }
}
