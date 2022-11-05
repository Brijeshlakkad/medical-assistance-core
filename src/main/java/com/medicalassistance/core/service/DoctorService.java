package com.medicalassistance.core.service;

import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.DoctorAppointment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.AlreadyExistsException;
import com.medicalassistance.core.mapper.AppointmentMapper;
import com.medicalassistance.core.repository.DoctorAppointmentRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.response.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DoctorService {
    @Autowired
    UserCommonService userCommonService;

    @Autowired
    DoctorAppointmentRepository appointmentRepository;

    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    UserRepository userRepository;

    public void storeDoctorAppointment(AppointmentRequest appointmentRequest) {
        if (appointmentRepository.existsByStartDateTimeBetweenOrStartDateTimeEqualsOrStartDateTimeEquals(
                appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime(),
                appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime()) ||
                appointmentRepository.existsByEndDateTimeBetweenOrEndDateTimeEqualsOrEndDateTimeEquals(
                        appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime(),
                        appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime())) {
            throw new AlreadyExistsException("conflict: doctor has the reserved time slot during the provided time period");
        }
        DoctorAppointment doctorAppointment = appointmentMapper.fromAppointmentRequestToDoctorAppointment(appointmentRequest);

        appointmentRepository.save(doctorAppointment);
    }

    public Page<AppointmentResponse> getDoctorAppointments(Pageable pageable) {
        User user = userCommonService.getUser();

        Page<DoctorAppointment> pages = appointmentRepository.findByDoctorIdAndStartDateTimeGreaterThanEqual(user.getUserId(), new Date(), pageable);

        return pages.map(appointmentMapper::toAppointmentResponse);
    }
}
