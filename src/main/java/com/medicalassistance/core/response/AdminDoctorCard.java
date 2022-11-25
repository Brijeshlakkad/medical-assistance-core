package com.medicalassistance.core.response;

public class AdminDoctorCard {
    UserCardResponse doctor;
    Integer currentPatients;

    public UserCardResponse getDoctor() {
        return doctor;
    }

    public void setDoctor(UserCardResponse doctor) {
        this.doctor = doctor;
    }

    public Integer getCurrentPatients() {
        return currentPatients;
    }

    public void setCurrentPatients(Integer currentPatients) {
        this.currentPatients = currentPatients;
    }
}