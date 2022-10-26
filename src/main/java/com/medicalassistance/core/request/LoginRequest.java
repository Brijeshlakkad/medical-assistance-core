package com.medicalassistance.core.request;

public class LoginRequest {
    String emailId;
    String password;

    public String getEmailId() {
        if (emailId != null && !emailId.isEmpty()) {
            return emailId.toLowerCase();
        }
        return null;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
