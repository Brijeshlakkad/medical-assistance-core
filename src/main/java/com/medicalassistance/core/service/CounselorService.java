package com.medicalassistance.core.service;

import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.CounselorAppointment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.mapper.AppointmentMapper;
import com.medicalassistance.core.repository.CounselorAppointmentRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.response.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CounselorService {
    @Autowired
    UserCommonService userCommonService;

    @Autowired
    CounselorAppointmentRepository counselorAppointmentRepository;

    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    UserRepository userRepository;

    public void storeCounselorAppointment(AppointmentRequest appointmentRequest) {
        CounselorAppointment counselorAppointment = appointmentMapper.fromAppointmentRequestToCounselorAppointment(appointmentRequest);

        counselorAppointmentRepository.save(counselorAppointment);
    }

    public Page<AppointmentResponse> getCounselorAppointments(Pageable pageable) {
        User user = userCommonService.getUser();

        Page<CounselorAppointment> pages = counselorAppointmentRepository.findByCounselorIdAndAppointmentDateGreaterThanEqual(user.getUserId(), new Date(), pageable);

        return pages.map(appointmentMapper::toAppointmentResponse);
    }
}
