package com.medicalassistance.core.mapper;


import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.DoctorAppointment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.response.DoctorAppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DoctorAppointmentMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCommonService userCommonService;

    public DoctorAppointmentResponse toDoctorAppointmentResponse(DoctorAppointment doctorAppointment) {
        DoctorAppointmentResponse doctorAppointmentResponse = new DoctorAppointmentResponse();
        doctorAppointmentResponse.setAppointmentDate(doctorAppointment.getAppointmentDate());
        doctorAppointmentResponse.setCreatedAt(doctorAppointment.getCreatedAt());
        doctorAppointmentResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(doctorAppointment.getPatientId())));
        return doctorAppointmentResponse;
    }


    public DoctorAppointment fromAppointmentRequest(AppointmentRequest appointmentRequest) {
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
}
