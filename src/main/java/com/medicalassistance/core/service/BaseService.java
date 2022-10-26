package com.medicalassistance.core.service;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.AlreadyExistsException;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.LoginRequest;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.LoginResponse;
import com.medicalassistance.core.security.JwtTokenUtil;
import com.medicalassistance.core.security.JwtUser;
import com.medicalassistance.core.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BaseService {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper patientMapper;

    public LoginResponse login(LoginRequest request, AuthorityName authorityName) {
        if (request == null) {
            return createErrorLoginResponse();
        }
        User savedUser = userRepository.findByEmailAddress(request.getEmailId());
        if (savedUser != null && savedUser.getAuthorities().contains(authorityName)) {
            if (checkValidLogin(savedUser, request.getPassword())) {
                return this.createSuccessLoginResponse(savedUser);
            } else {
                return this.createErrorLoginResponse();
            }
        } else {
            return this.createErrorLoginResponse("User doesn't exist. Please sign up.");
        }
    }

    public LoginResponse signUp(UserRequest userRequest, AuthorityName authorityName) {
        User patient = patientMapper.fromPatientRequest(userRequest);
        if (patient == null || patient.getEmailAddress() == null || patient.getPassword() == null) {
            return this.createErrorLoginResponse("Invalid user request");
        }
        try {
            this.checkIfEmailIsTakenWithException(patient.getEmailAddress());
        } catch (AlreadyExistsException e) {
            return this.createErrorLoginResponse(e.getMessage());
        }

        Set<AuthorityName> authorities = new HashSet<>();
        authorities.add(authorityName);
        patient.setAuthorities(authorities);

        // For encrypting the password
        String encPassword = EncryptionUtil.encryptPassword(patient.getPassword());
        if (encPassword != null) {
            patient.setPassword(encPassword);
        }

        User savedUser = userRepository.save(patient);
        return this.createSuccessLoginResponse(savedUser);
    }

    private boolean checkIfEmailIsTaken(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    public void checkIfEmailIsTakenWithException(String email) {
        if (this.checkIfEmailIsTaken(email)) {
            throw new AlreadyExistsException("User already exists");
        }
    }

    public LoginResponse createSuccessLoginResponse(User savedUser) {
        LoginResponse response = new LoginResponse();
        response.setEmailAddress(savedUser.getEmailAddress());
        response.setLoginSuccess(true);
        JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(savedUser.getEmailAddress());
        response.setAccessToken(jwtTokenUtil.generateToken(userDetails));
        return response;
    }

    public LoginResponse createErrorLoginResponse() {
        return this.createErrorLoginResponse("Wrong credentials!");
    }

    public LoginResponse createErrorLoginResponse(String errorMessage) {
        LoginResponse response = new LoginResponse();
        response.setLoginSuccess(false);
        response.setErrorMessage(errorMessage);
        return response;
    }

    private boolean checkValidLogin(User user, String password) {
        String userPassword = user.getPassword();
        return userPassword != null && EncryptionUtil.isValidPassword(password, userPassword);
    }
}
