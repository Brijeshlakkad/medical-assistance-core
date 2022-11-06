package com.medicalassistance.core.controller;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.request.AssessmentResultRequest;
import com.medicalassistance.core.request.LoginRequest;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.AssessmentResponse;
import com.medicalassistance.core.response.LoginResponse;
import com.medicalassistance.core.response.PatientRecordStatusResponse;
import com.medicalassistance.core.response.UserCardResponse;
import com.medicalassistance.core.security.JwtTokenUtil;
import com.medicalassistance.core.service.AssessmentService;
import com.medicalassistance.core.service.BaseService;
import com.medicalassistance.core.service.PatientService;
import com.medicalassistance.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/patient")
public class PatientController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BaseService baseService;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return baseService.login(request, AuthorityName.ROLE_PATIENT);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public LoginResponse signup(@Valid @RequestBody UserRequest request) {
        return baseService.signUp(request, AuthorityName.ROLE_PATIENT);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public UserCardResponse getProfileCard() {
        return userService.getProfileCard();
    }

    @RequestMapping(value = "/assessment/{assessmentId}", method = RequestMethod.GET)
    public AssessmentResponse getAssessment(@PathVariable String assessmentId) {
        return assessmentService.getAssessment(assessmentId);
    }

    @RequestMapping(value = "/assessment/{assessmentId}", method = RequestMethod.POST)
    public void storeAssessment(@PathVariable String assessmentId, @Valid @RequestBody AssessmentResultRequest assessmentResultRequest) {
        assessmentService.storeAssessmentResult(assessmentId, assessmentResultRequest);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public PatientRecordStatusResponse getStatus() {
        return patientService.getPatientRecordStatus();
    }
}