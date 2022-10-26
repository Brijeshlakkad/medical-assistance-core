package com.medicalassistance.core.mapper;


import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User fromPatientRequest(UserRequest userRequest) {
        User patient = new User();
        patient.setFullName(userRequest.getFullName());
        patient.setEmailAddress(userRequest.getEmailAddress());
        patient.setPassword(userRequest.getPassword());
        patient.setAddressLine1(userRequest.getAddressLine1());
        patient.setAddressLine2(userRequest.getAddressLine2());
        patient.setCity(userRequest.getCity());
        patient.setProvince(userRequest.getProvince());
        patient.setCountry(userRequest.getCountry());
        patient.setZipCode(userRequest.getZipCode());
        patient.setDateOfBirth(userRequest.getDateOfBirth());
        patient.setPhoneNumber(userRequest.getPhoneNumber());
        return patient;
    }
}
