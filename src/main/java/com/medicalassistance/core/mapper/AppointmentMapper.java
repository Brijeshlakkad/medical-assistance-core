package com.medicalassistance.core.mapper;


import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.*;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.repository.ActivePatientRepository;
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
    private ActivePatientRepository activePatientRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCommonService userCommonService;

    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        ActivePatient activePatient = activePatientRepository.findByActivePatientId(appointment.getAppointmentId());
        if (activePatient != null) {
            AppointmentResponse appointmentResponse = new AppointmentResponse();
            appointmentResponse.setStartDateTime(appointment.getStartDateTime());
            appointmentResponse.setEndDateTime(appointment.getEndDateTime());
            appointmentResponse.setCreatedAt(appointment.getCreatedAt());
            appointmentResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(activePatient.getPatientRecord().getPatientId())));
            return appointmentResponse;
        }
        throw new ResourceNotFoundException("Patient record doesn't found");
    }

    public DoctorAppointment fromAppointmentRequestToDoctorAppointment(AppointmentRequest appointmentRequest) {
        User user = userCommonService.getUser();
        if (userRepository.existsByUserId(appointmentRequest.getActivePatientId())) {
            DoctorAppointment doctorAppointment = new DoctorAppointment();
            doctorAppointment.setStartDateTime(appointmentRequest.getStartDateTime());
            doctorAppointment.setEndDateTime(appointmentRequest.getEndDateTime());
            doctorAppointment.setDoctorId(user.getUserId());
            doctorAppointment.setCreatedAt(new Date());
            doctorAppointment.setActivePatientId(appointmentRequest.getActivePatientId());
            return doctorAppointment;
        }
        throw new ResourceNotFoundException(String.format("patient %s not found", appointmentRequest.getActivePatientId()));
    }

    public CounselorAppointment fromAppointmentRequestToCounselorAppointment(AppointmentRequest appointmentRequest) {
        User user = userCommonService.getUser();
        if (userRepository.existsByUserId(appointmentRequest.getActivePatientId())) {
            CounselorAppointment counselorAppointment = new CounselorAppointment();
            counselorAppointment.setStartDateTime(appointmentRequest.getStartDateTime());
            counselorAppointment.setEndDateTime(appointmentRequest.getEndDateTime());
            counselorAppointment.setCounselorId(user.getUserId());
            counselorAppointment.setCreatedAt(new Date());
            counselorAppointment.setActivePatientId(appointmentRequest.getActivePatientId());
            return counselorAppointment;
        }
        throw new ResourceNotFoundException(String.format("patient %s not found", appointmentRequest.getActivePatientId()));
    }
}