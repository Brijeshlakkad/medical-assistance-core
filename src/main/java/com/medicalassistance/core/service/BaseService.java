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

import java.util.Date;
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
    private UserMapper userMapper;

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
        User patient = userMapper.fromPatientRequest(userRequest);
        if (patient == null) {
            return this.createErrorLoginResponse("Invalid user request");
        } else if (patient.getEmailAddress() == null) {
            return this.createErrorLoginResponse("Invalid email address");
        } else if (patient.getPassword() == null) {
            return this.createErrorLoginResponse("Invalid user password");
        } else if (patient.getDateOfBirth() == null) {
            return this.createErrorLoginResponse("Invalid date of birth");
        } else if (patient.getFullName() == null) {
            return this.createErrorLoginResponse("Invalid full name");
        } else if (patient.getCity() == null) {
            return this.createErrorLoginResponse("Invalid city");
        } else if (patient.getCountry() == null) {
            return this.createErrorLoginResponse("Invalid country");
        } else if (patient.getPhoneNumber() == null) {
            return this.createErrorLoginResponse("Invalid phone number");
        } else if (patient.getProvince() == null) {
            return this.createErrorLoginResponse("Invalid province");
        }

        try {
            this.checkIfEmailIsTakenWithException(patient.getEmailAddress());
        } catch (AlreadyExistsException e) {
            return this.createErrorLoginResponse(e.getMessage());
        }

        Set<AuthorityName> authorities = new HashSet<>();
        authorities.add(authorityName);
        patient.setAuthorities(authorities);
        patient.setCreatedAt(new Date());
        patient.setModifiedAt(new Date());
        patient.setLastPasswordResetDate(new Date());

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
        response.setUser(userMapper.toUserCardResponse(savedUser));
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
