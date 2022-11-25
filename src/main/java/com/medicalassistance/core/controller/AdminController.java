package com.medicalassistance.core.controller;

import com.medicalassistance.core.entity.Assessment;
import com.medicalassistance.core.request.UserRequest;
import com.medicalassistance.core.response.AdminCounselorCard;
import com.medicalassistance.core.response.AdminDoctorCard;
import com.medicalassistance.core.response.AdminPatientCard;
import com.medicalassistance.core.response.AdminUserCreateResponse;
import com.medicalassistance.core.security.JwtTokenUtil;
import com.medicalassistance.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public Page<AdminPatientCard> getPatients(@RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return adminService.getPatients(paging);
    }

    @RequestMapping(value = "/counselor", method = RequestMethod.GET)
    public Page<AdminCounselorCard> getCounselors(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return adminService.getCounselors(paging);
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public Page<AdminDoctorCard> getDoctors(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return adminService.getDoctors(paging);
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