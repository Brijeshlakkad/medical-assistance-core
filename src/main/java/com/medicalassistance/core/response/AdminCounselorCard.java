package com.medicalassistance.core.response;

public class AdminCounselorCard {
    UserCardResponse counselor;
    Integer currentPatients;

    public UserCardResponse getCounselor() {
        return counselor;
    }

    public void setCounselor(UserCardResponse counselor) {
        this.counselor = counselor;
    }

    public Integer getCurrentPatients() {
        return currentPatients;
    }

    public void setCurrentPatients(Integer currentPatients) {
        this.currentPatients = currentPatients;
    }
}