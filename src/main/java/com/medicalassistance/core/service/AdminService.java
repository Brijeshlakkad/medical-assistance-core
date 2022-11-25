package com.medicalassistance.core.service;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.Assessment;
import com.medicalassistance.core.repository.AssessmentRepository;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.AdminUserCreateResponse;
import com.medicalassistance.core.response.LoginResponse;
import com.medicalassistance.core.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private BaseService baseService;

    public void createAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
    }

    public AdminUserCreateResponse createPatient(UserRequest userRequest) {
        return createUser(userRequest, AuthorityName.ROLE_PATIENT);
    }

    public AdminUserCreateResponse createCounselor(UserRequest userRequest) {
        return createUser(userRequest, AuthorityName.ROLE_COUNSELOR);
    }

    public AdminUserCreateResponse createDoctor(UserRequest userRequest) {
        return createUser(userRequest, AuthorityName.ROLE_DOCTOR);
    }

    private AdminUserCreateResponse createUser(UserRequest userRequest, AuthorityName authorityName) {
        userRequest.setPassword(UserUtil.generateRandomPassword());
        LoginResponse loginResponse = baseService.signUp(userRequest, authorityName);
        AdminUserCreateResponse response = new AdminUserCreateResponse();
        response.setSuccess(loginResponse.isLoginSuccess());
        response.setErrorMessage(loginResponse.getErrorMessage());
        response.setUser(loginResponse.getUser());
        return response;
    }
}