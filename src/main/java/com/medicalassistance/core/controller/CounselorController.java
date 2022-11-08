package com.medicalassistance.core.controller;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.request.DoctorAssignmentRequest;
import com.medicalassistance.core.request.LoginRequest;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.*;
import com.medicalassistance.core.security.JwtTokenUtil;
import com.medicalassistance.core.service.BaseService;
import com.medicalassistance.core.service.CounselorService;
import com.medicalassistance.core.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/counselor")
public class CounselorController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BaseService baseService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private CounselorService counselorService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return baseService.login(request, AuthorityName.ROLE_COUNSELOR);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public LoginResponse signup(@Valid @RequestBody UserRequest request) {
        return baseService.signUp(request, AuthorityName.ROLE_COUNSELOR);
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public Page<PatientRecordCardResponse> getPatientList(@RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return patientService.getActivePatients(paging);
    }

    @RequestMapping(value = "/patient/{activePatientId}", method = RequestMethod.GET)
    public PatientRecordResponse getPatientRecord(@PathVariable String activePatientId) {
        return patientService.getActivePatient(activePatientId);
    }

    @RequestMapping(value = "/patient/appointment", method = RequestMethod.GET)
    public Page<AppointmentResponse> getDoctorAppointments(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return counselorService.getCounselorAppointments(paging);
    }

    @RequestMapping(value = "/patient/appointment", method = RequestMethod.POST)
    public void makeDoctorAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        counselorService.storeCounselorAppointment(appointmentRequest);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public Page<CounselorDoctorCardResponse> getDoctorPage(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return counselorService.getDoctorPage(paging);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST)
    public void assignDoctorToPatient(@Valid @RequestBody DoctorAssignmentRequest doctorAssignmentRequest) {
        counselorService.assignDoctorToPatient(doctorAssignmentRequest);
    }
}