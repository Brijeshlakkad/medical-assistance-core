package com.medicalassistance.core.controller;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.request.LoginRequest;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.LoginResponse;
import com.medicalassistance.core.security.JwtTokenUtil;
import com.medicalassistance.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/doctor")
public class DoctorController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return baseService.login(request, AuthorityName.ROLE_DOCTOR);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public LoginResponse signup(@Valid @RequestBody UserRequest request) {
        return baseService.signUp(request, AuthorityName.ROLE_DOCTOR);
    }
}