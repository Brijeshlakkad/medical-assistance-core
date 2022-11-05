package com.medicalassistance.core.service;

import com.medicalassistance.core.common.PatientRecordStatus;
import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.DoctorAppointment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.AlreadyExistsException;
import com.medicalassistance.core.exception.InvalidUserRequestException;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.mapper.AppointmentMapper;
import com.medicalassistance.core.repository.ActivePatientRepository;
import com.medicalassistance.core.repository.DoctorAppointmentRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.response.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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

    @Autowired
    ActivePatientRepository activePatientRepository;

    @Autowired
    PatientRecordService patientRecordService;

    public void storeDoctorAppointment(AppointmentRequest appointmentRequest) {
        if (appointmentRequest.getStartDateTime().toInstant().toEpochMilli() <= (new Date()).getTime() ||
                appointmentRequest.getStartDateTime().isAfter(appointmentRequest.getEndDateTime()) ||
                appointmentRequest.getStartDateTime().isEqual(appointmentRequest.getEndDateTime())
        ) {
            throw new InvalidUserRequestException("appointment time period invalid");
        }
        if (activePatientRepository.existsByActivePatientId(appointmentRequest.getActivePatientId())) {
            if (appointmentRepository.existsByStartDateTimeBetweenOrStartDateTimeEqualsOrStartDateTimeEquals(
                    appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime(),
                    appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime()) ||
                    appointmentRepository.existsByEndDateTimeBetweenOrEndDateTimeEqualsOrEndDateTimeEquals(
                            appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime(),
                            appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime())) {
                throw new AlreadyExistsException("conflict: doctor has the reserved time slot during the provided time period");
            }
            // save doctor appointment
            DoctorAppointment doctorAppointment = appointmentMapper.fromAppointmentRequestToDoctorAppointment(appointmentRequest);
            doctorAppointment = appointmentRepository.save(doctorAppointment);

            // update patient record
            patientRecordService.afterAppointment(doctorAppointment, PatientRecordStatus.DOCTOR_APPOINTMENT);
            return;
        }
        throw new ResourceNotFoundException(String.format("patient record %s not found", appointmentRequest.getActivePatientId()));
    }

    public Page<AppointmentResponse> getDoctorAppointments(Pageable pageable) {
        User user = userCommonService.getUser();

        Page<DoctorAppointment> pages = appointmentRepository.findByDoctorIdAndStartDateTimeGreaterThanEqual(user.getUserId(), ZonedDateTime.now(), pageable);

        return pages.map(appointmentMapper::toAppointmentResponse);
    }
}
