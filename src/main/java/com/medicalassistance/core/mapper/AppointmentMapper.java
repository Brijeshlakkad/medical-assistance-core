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
        ActivePatient activePatient = activePatientRepository.findByActivePatientId(appointment.getActivePatientId());
        if (activePatient != null) {
            AppointmentResponse appointmentResponse = new AppointmentResponse();
            appointmentResponse.setStartDateTime(appointment.getStartDateTime());
            appointmentResponse.setEndDateTime(appointment.getEndDateTime());
            appointmentResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(activePatient.getPatientId())));
            return appointmentResponse;
        }
        throw new ResourceNotFoundException("active patient record doesn't found");
    }

    public DoctorAppointment fromAppointmentRequestToDoctorAppointment(AppointmentRequest appointmentRequest) {
        User user = userCommonService.getUser();
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        doctorAppointment.setStartDateTime(appointmentRequest.getStartDateTime());
        doctorAppointment.setEndDateTime(appointmentRequest.getEndDateTime());
        doctorAppointment.setDoctorId(user.getUserId());
        doctorAppointment.setActivePatientId(appointmentRequest.getActivePatientId());
        return doctorAppointment;
    }

    public CounselorAppointment fromAppointmentRequestToCounselorAppointment(AppointmentRequest appointmentRequest) {
        User user = userCommonService.getUser();
        CounselorAppointment counselorAppointment = new CounselorAppointment();
        counselorAppointment.setStartDateTime(appointmentRequest.getStartDateTime());
        counselorAppointment.setEndDateTime(appointmentRequest.getEndDateTime());
        counselorAppointment.setCounselorId(user.getUserId());
        counselorAppointment.setActivePatientId(appointmentRequest.getActivePatientId());
        return counselorAppointment;
    }
}