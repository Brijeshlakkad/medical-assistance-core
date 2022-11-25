package com.medicalassistance.core.controller;

import com.medicalassistance.core.entity.Assessment;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.AdminUserCreateResponse;
import com.medicalassistance.core.security.JwtTokenUtil;
import com.medicalassistance.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/assessment", method = RequestMethod.POST)
    public void createAssessment(@Valid @RequestBody Assessment assessment) {
        adminService.createAssessment(assessment);
    }

    @RequestMapping(value = "/patient", method = RequestMethod.POST)
    public AdminUserCreateResponse createPatient(@Valid @RequestBody UserRequest userRequest) {
        return adminService.createPatient(userRequest);
    }

    @RequestMapping(value = "/counselor", method = RequestMethod.POST)
    public AdminUserCreateResponse createCounselor(@Valid @RequestBody UserRequest userRequest) {
        return adminService.createCounselor(userRequest);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST)
    public AdminUserCreateResponse createDoctor(@Valid @RequestBody UserRequest userRequest) {
        return adminService.createDoctor(userRequest);
    }
}