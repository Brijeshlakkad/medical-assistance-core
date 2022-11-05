package com.medicalassistance.core.controller;

import com.medicalassistance.core.entity.Assessment;
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
}