package com.medicalassistance.core.mapper;


import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.Appointment;
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

    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setStartDateTime(appointment.getStartDateTime());
        appointmentResponse.setEndDateTime(appointment.getEndDateTime());
        appointmentResponse.setCreatedAt(appointment.getCreatedAt());
        appointmentResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(appointment.getPatientId())));
        return appointmentResponse;
    }

    public DoctorAppointment fromAppointmentRequestToDoctorAppointment(AppointmentRequest appointmentRequest) {
        User user = userCommonService.getUser();
        if (userRepository.existsByUserId(appointmentRequest.getPatientId())) {
            DoctorAppointment doctorAppointment = new DoctorAppointment();
            doctorAppointment.setStartDateTime(appointmentRequest.getStartDateTime());
            doctorAppointment.setEndDateTime(appointmentRequest.getEndDateTime());
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
            counselorAppointment.setStartDateTime(appointmentRequest.getStartDateTime());
            counselorAppointment.setEndDateTime(appointmentRequest.getEndDateTime());
            counselorAppointment.setCounselorId(user.getUserId());
            counselorAppointment.setCreatedAt(new Date());
            counselorAppointment.setPatientId(appointmentRequest.getPatientId());
            return counselorAppointment;
        }
        throw new ResourceNotFoundException(String.format("patient %s not found", appointmentRequest.getPatientId()));
    }
}
