package com.medicalassistance.core.response;

public class LoginResponse {
    boolean loginSuccess;
    String accessToken;
    String errorMessage;
    UserCardResponse user;

    public LoginResponse() {

    }

    public LoginResponse(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "LoginResponse [loginSuccess=" + loginSuccess + ",  accessToken=" + accessToken + "]";
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
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