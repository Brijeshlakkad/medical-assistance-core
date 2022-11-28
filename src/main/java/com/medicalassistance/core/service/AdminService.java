package com.medicalassistance.core.service;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.Assessment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.*;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.*;
import com.medicalassistance.core.util.TimeUtil;
import com.medicalassistance.core.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ActivePatientRepository activePatientRepository;

    @Autowired
    private CounselorAppointmentRepository counselorAppointmentRepository;

    @Autowired
    private DoctorAppointmentRepository doctorAppointmentRepository;

    @Autowired
    private AssignedPatientRepository assignedPatientRepository;

    public void createAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
    }

    public Page<AdminPatientCard> getPatients(Pageable pageable) {
        Page<User> patientCardPage = userRepository.findByAuthoritiesContainsAndDeletedFalseOrderByModifiedAt(AuthorityName.ROLE_PATIENT, pageable);
        return patientCardPage.map(userMapper::toAdminPatientCard);
    }

    public Page<AdminCounselorCard> getCounselors(Pageable pageable) {
        Page<User> patientCardPage = userRepository.findByAuthoritiesContainsAndDeletedFalseOrderByModifiedAt(AuthorityName.ROLE_COUNSELOR, pageable);
        return patientCardPage.map(userMapper::toAdminCounselorCard);
    }

    public Page<AdminDoctorCard> getDoctors(Pageable pageable) {
        Page<User> patientCardPage = userRepository.findByAuthoritiesContainsAndDeletedFalseOrderByModifiedAt(AuthorityName.ROLE_DOCTOR, pageable);
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

    public void removePatient(String emailAddress) {
        removeUser(emailAddress, AuthorityName.ROLE_PATIENT);
    }

    public void removeCounselor(String emailAddress) {
        removeUser(emailAddress, AuthorityName.ROLE_COUNSELOR);
    }

    public void removeDoctor(String emailAddress) {
        removeUser(emailAddress, AuthorityName.ROLE_DOCTOR);
    }

    private void removeUser(String emailAddress, AuthorityName authorityName) {
        User user = userRepository.findByEmailAddressAndAuthoritiesContainsAndDeletedFalse(emailAddress, Collections.singleton(authorityName));
        if (user != null) {
            user.setDeleted(true);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("user not found!");
        }
    }

    public AdminPatientReport getAdminPatientReportByRange(Date startDateTime, Date endDateTime) {
        AdminPatientReport report = new AdminPatientReport();
        List<User> patientCardPage = userRepository.findByAuthoritiesContainsAndCreatedAtBetweenAndDeletedFalseOrderByCreatedAt
                (Collections.singleton(AuthorityName.ROLE_PATIENT),
                        startDateTime,
                        endDateTime);
        report.setPatients(patientCardPage.stream().map(userMapper::toAdminPatientCard).collect(Collectors.toList()));
        report.setNumAttemptedAssessment(activePatientRepository.countBy());
        report.setNumTotal(patientCardPage.size());
        report.setNumHasCounselorAppointment(counselorAppointmentRepository.countByStartDateTimeAfter(TimeUtil.nowUTC()));
        Integer numHasDoctorAppointment = doctorAppointmentRepository.countByStartDateTimeAfter(TimeUtil.nowUTC());
        report.setNumHasDoctorAppointment(numHasDoctorAppointment);
        report.setNumInProcessingDoctorAppointment(assignedPatientRepository.countBy() - numHasDoctorAppointment);
        return report;
    }

    public AdminPatientReportParameters getAdminPatientReportParameters() {
        AdminPatientReportParameters report = new AdminPatientReportParameters();
        Integer totalUsers = userRepository.countByAuthoritiesContains(Collections.singleton(AuthorityName.ROLE_PATIENT));
        report.setNumAttemptedAssessment(activePatientRepository.countBy());
        report.setNumTotal(totalUsers);
        report.setNumHasCounselorAppointment(counselorAppointmentRepository.countByStartDateTimeAfter(TimeUtil.nowUTC()));
        Integer numHasDoctorAppointment = doctorAppointmentRepository.countByStartDateTimeAfter(TimeUtil.nowUTC());
        report.setNumHasDoctorAppointment(numHasDoctorAppointment);
        report.setNumInProcessingDoctorAppointment(assignedPatientRepository.countBy() - numHasDoctorAppointment);
        return report;
    }

    public void resetUsers() {
        userRepository.findAll().forEach(user -> {
            user.setDeleted(false);
            user.setPasswordAutoGenerated(false);
            userRepository.save(user);
        });
    }
}