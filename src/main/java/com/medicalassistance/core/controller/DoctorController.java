package com.medicalassistance.core.controller;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.request.LoginRequest;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.AppointmentResponse;
import com.medicalassistance.core.response.AssignedPatientResponse;
import com.medicalassistance.core.response.LoginResponse;
import com.medicalassistance.core.response.PatientRecordResponse;
import com.medicalassistance.core.security.JwtTokenUtil;
import com.medicalassistance.core.service.BaseService;
import com.medicalassistance.core.service.DoctorService;
import com.medicalassistance.core.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/doctor")
public class DoctorController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BaseService baseService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return baseService.login(request, AuthorityName.ROLE_DOCTOR);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public LoginResponse signup(@Valid @RequestBody UserRequest request) {
        return baseService.signUp(request, AuthorityName.ROLE_DOCTOR);
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public Page<AssignedPatientResponse> getAssignedPatients(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return doctorService.getAssignedPatients(paging);
    }

    @RequestMapping(value = "/patient/{activePatientId}", method = RequestMethod.GET)
    public PatientRecordResponse getPatientRecord(@PathVariable String activePatientId) {
        return patientService.getActivePatient(activePatientId);
    }

    @RequestMapping(value = "/patient/appointment", method = RequestMethod.GET)
    public Page<AppointmentResponse> getDoctorAppointments(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return doctorService.getDoctorAppointments(paging);
    }

    @RequestMapping(value = "/patient/appointment", method = RequestMethod.POST)
    public void makeDoctorAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        doctorService.storeDoctorAppointment(appointmentRequest);
    }
}