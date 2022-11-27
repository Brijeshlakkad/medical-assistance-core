package com.medicalassistance.core.response;

import org.springframework.data.domain.Page;

public class AdminPatientReport {
    Page<AdminPatientCard> patients;

    long numAttemptedAssessment;

    long numHasCounselorAppointment;

    long numInProcessingDoctorAppointment;

    long numHasDoctorAppointment;

    long numTotal;

    public Page<AdminPatientCard> getPatients() {
        return patients;
    }

    public void setPatients(Page<AdminPatientCard> patients) {
        this.patients = patients;
    }

    public long getNumAttemptedAssessment() {
        return numAttemptedAssessment;
    }

    public void setNumAttemptedAssessment(long numAttemptedAssessment) {
        this.numAttemptedAssessment = numAttemptedAssessment;
    }

    public long getNumHasCounselorAppointment() {
        return numHasCounselorAppointment;
    }

    public void setNumHasCounselorAppointment(long numHasCounselorAppointment) {
        this.numHasCounselorAppointment = numHasCounselorAppointment;
    }

    public long getNumInProcessingDoctorAppointment() {
        return numInProcessingDoctorAppointment;
    }

    public void setNumInProcessingDoctorAppointment(long numInProcessingDoctorAppointment) {
        this.numInProcessingDoctorAppointment = numInProcessingDoctorAppointment;
    }

    public long getNumHasDoctorAppointment() {
        return numHasDoctorAppointment;
    }

    public void setNumHasDoctorAppointment(long numHasDoctorAppointment) {
        this.numHasDoctorAppointment = numHasDoctorAppointment;
    }

    public long getNumTotal() {
        return numTotal;
    }

    public void setNumTotal(long numTotal) {
        this.numTotal = numTotal;
    }
}
