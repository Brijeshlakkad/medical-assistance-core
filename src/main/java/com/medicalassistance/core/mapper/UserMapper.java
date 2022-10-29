package com.medicalassistance.core.mapper;


import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.UserCardResponse;
import com.medicalassistance.core.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {
    public User fromPatientRequest(UserRequest userRequest) {
        User patient = new User();
        patient.setFullName(userRequest.getFullName());
        patient.setEmailAddress(userRequest.getEmailAddress());
        patient.setPassword(userRequest.getPassword());
        patient.setAddressLine(userRequest.getAddressLine1());
        patient.setCity(userRequest.getCity());
        patient.setProvince(userRequest.getProvince());
        patient.setCountry(userRequest.getCountry());
        patient.setDateOfBirth(userRequest.getDateOfBirth());
        patient.setPhoneNumber(userRequest.getPhoneNumber());
        return patient;
    }

    public UserCardResponse toUserCardResponse(User user) {
        UserCardResponse response = new UserCardResponse();
        response.setFullName(user.getFullName());
        response.setEmailAddress(user.getEmailAddress());
        response.setAddressLine(user.getAddressLine());
        response.setCity(user.getCity());
        response.setProvince(user.getProvince());
        response.setCountry(user.getCountry());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAge((new Date()).getYear() - user.getDateOfBirth().getYear());
        return response;
    }

    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setFullName(user.getFullName());
        response.setEmailAddress(user.getEmailAddress());
        response.setAddressLine(user.getAddressLine());
        response.setCity(user.getCity());
        response.setProvince(user.getProvince());
        response.setCountry(user.getCountry());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAge((new Date()).getYear() - user.getDateOfBirth().getYear());
        return response;
    }
}
