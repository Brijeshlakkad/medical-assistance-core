package com.medicalassistance.core.response;

import java.util.ArrayList;
import java.util.List;

public class PatientRecordCardListResponse {
    List<PatientRecordCardResponse> patients = new ArrayList<>();

    public List<PatientRecordCardResponse> getPatients() {
        return patients;
    }

    public boolean addPatientRecordCardResponse(PatientRecordCardResponse cardResponse){
        return this.patients.add(cardResponse);
    }
}
