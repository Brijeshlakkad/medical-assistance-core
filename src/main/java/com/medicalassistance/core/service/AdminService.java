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

    public AdminPatientReport getAdminPatientReport(Date startDateTime, Date endDateTime, Pageable pageable) {
        //        Date currentWeekStart, currentWeekEnd;
        //        Calendar currentCalendar = Calendar.getInstance();
        //        currentCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        //        while (currentCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
        //            currentCalendar.add(Calendar.DATE, -1);   //go one day before
        //        }
        //        currentWeekStart = currentCalendar.getTime();
        //        ZonedDateTime weekStartDateTime = ZonedDateTime.ofInstant(currentWeekStart.toInstant(), ZoneOffset.UTC);
        //
        //        currentCalendar.add(Calendar.DATE, 6);    //add 6 days after Monday
        //        currentWeekEnd = currentCalendar.getTime();
        //        ZonedDateTime weekEndDateTime = ZonedDateTime.ofInstant(currentWeekEnd.toInstant(), ZoneOffset.UTC);

        AdminPatientReport report = new AdminPatientReport();
        Page<User> patientCardPage = userRepository.findByAuthoritiesContainsAndCreatedAtBetweenAndDeletedFalseOrderByCreatedAt
                (Collections.singleton(AuthorityName.ROLE_PATIENT),
                        startDateTime,
                        endDateTime,
                        pageable);
        report.setPatients(patientCardPage.map(userMapper::toAdminPatientCard));
        report.setNumAttemptedAssessment(activePatientRepository.countBy());
        report.setNumTotal(patientCardPage.getTotalElements());
        report.setNumHasCounselorAppointment(counselorAppointmentRepository.countByStartDateTimeAfter(TimeUtil.nowUTC()));
        Integer numHasDoctorAppointment = doctorAppointmentRepository.countByStartDateTimeAfter(TimeUtil.nowUTC());
        report.setNumHasDoctorAppointment(numHasDoctorAppointment);
        report.setNumInProcessingDoctorAppointment(assignedPatientRepository.countBy() - numHasDoctorAppointment);
        return report;
    }
}