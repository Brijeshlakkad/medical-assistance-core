package com.medicalassistance.core.service;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.Assessment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.AssessmentRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.*;
import com.medicalassistance.core.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private BaseService baseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void createAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
    }

    public Page<AdminPatientCard> getPatients(Pageable pageable) {
        Page<User> patientCardPage = userRepository.findByAuthoritiesContains(AuthorityName.ROLE_PATIENT, pageable);
        return patientCardPage.map(userMapper::toAdminPatientCard);
    }

    public Page<AdminCounselorCard> getCounselors(Pageable pageable) {
        Page<User> patientCardPage = userRepository.findByAuthoritiesContains(AuthorityName.ROLE_PATIENT, pageable);
        return patientCardPage.map(userMapper::toAdminCounselorCard);
    }

    public Page<AdminDoctorCard> getDoctors(Pageable pageable) {
        Page<User> patientCardPage = userRepository.findByAuthoritiesContains(AuthorityName.ROLE_PATIENT, pageable);
        return patientCardPage.map(userMapper::toAdminDoctorCard);
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
        LoginResponse loginResponse = baseService.signUp(userRequest, authorityName, true);
        AdminUserCreateResponse response = new AdminUserCreateResponse();
        response.setSuccess(loginResponse.isLoginSuccess());
        response.setErrorMessage(loginResponse.getErrorMessage());
        response.setUser(loginResponse.getUser());
        return response;
    }
}