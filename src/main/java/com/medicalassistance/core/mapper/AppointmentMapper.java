package com.medicalassistance.core.mapper;


import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.CounselorAppointment;
import com.medicalassistance.core.entity.DoctorAppointment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.response.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppointmentMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCommonService userCommonService;

    public AppointmentResponse toAppointmentResponse(DoctorAppointment doctorAppointment) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentDate(doctorAppointment.getAppointmentDate());
        appointmentResponse.setCreatedAt(doctorAppointment.getCreatedAt());
        appointmentResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(doctorAppointment.getPatientId())));
        return appointmentResponse;
    }

    public AppointmentResponse toAppointmentResponse(CounselorAppointment counselorAppointment) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentDate(counselorAppointment.getAppointmentDate());
        appointmentResponse.setCreatedAt(counselorAppointment.getCreatedAt());
        appointmentResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(counselorAppointment.getPatientId())));
        return appointmentResponse;
    }


    public DoctorAppointment fromAppointmentRequestToDoctorAppointment(AppointmentRequest appointmentRequest) {
        User user = userCommonService.getUser();
        if (userRepository.existsByUserId(appointmentRequest.getPatientId())) {
            DoctorAppointment doctorAppointment = new DoctorAppointment();
            doctorAppointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
            doctorAppointment.setDoctorId(user.getUserId());
            doctorAppointment.setCreatedAt(new Date());
            doctorAppointment.setPatientId(appointmentRequest.getPatientId());
            return doctorAppointment;
        }
        throw new ResourceNotFoundException(String.format("patient %s not found", appointmentRequest.getPatientId()));
    }

    public CounselorAppointment fromAppointmentRequestToCounselorAppointment(AppointmentRequest appointmentRequest) {
        User user = userCommonService.getUser();
        if (userRepository.existsByUserId(appointmentRequest.getPatientId())) {
            CounselorAppointment counselorAppointment = new CounselorAppointment();
            counselorAppointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
            counselorAppointment.setCounselorId(user.getUserId());
            counselorAppointment.setCreatedAt(new Date());
            counselorAppointment.setPatientId(appointmentRequest.getPatientId());
            return counselorAppointment;
        }
        throw new ResourceNotFoundException(String.format("patient %s not found", appointmentRequest.getPatientId()));
    }
}
