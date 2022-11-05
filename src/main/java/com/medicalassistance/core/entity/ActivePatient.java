package com.medicalassistance.core.entity;

import com.medicalassistance.core.common.ActivePatientRecordStatus;
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
    private PatientRecord patientRecord;

    private ActivePatientRecordStatus status = ActivePatientRecordStatus.COUNSELOR_IN_PROGRESS;

    private String relatedKey;

    public String getActivePatientId() {
        return activePatientId;
    }

    public void setActivePatientId(String activePatientId) {
        this.activePatientId = activePatientId;
    }

    public PatientRecord getPatientRecord() {
        return patientRecord;
    }

    public void setPatientRecord(PatientRecord patientRecord) {
        this.patientRecord = patientRecord;
    }

    public ActivePatientRecordStatus getStatus() {
        return status;
    }

    public void setStatus(ActivePatientRecordStatus status) {
        this.status = status;
    }

    public String getRelatedKey() {
        return relatedKey;
    }

    public void setRelatedKey(String relatedKey) {
        this.relatedKey = relatedKey;
    }
}
