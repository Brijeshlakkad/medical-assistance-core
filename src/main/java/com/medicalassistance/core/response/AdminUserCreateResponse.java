package com.medicalassistance.core.response;

public class AdminUserCreateResponse {
    boolean success;
    String errorMessage;
    UserCardResponse user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public UserCardResponse getUser() {
        return user;
    }

    public void setUser(UserCardResponse user) {
        this.user = user;
    }
}