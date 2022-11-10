package com.medicalassistance.core.service;

import com.medicalassistance.core.common.PatientRecordStatus;
import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.AssignedPatient;
import com.medicalassistance.core.entity.DoctorAppointment;
import com.medicalassistance.core.entity.PatientRecord;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.AlreadyExistsException;
import com.medicalassistance.core.exception.InvalidUserRequestException;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.mapper.AppointmentMapper;
import com.medicalassistance.core.mapper.AssignedPatientMapper;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.AssignedPatientRepository;
import com.medicalassistance.core.repository.DoctorAppointmentRepository;
import com.medicalassistance.core.repository.PatientRecordRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.response.AppointmentResponse;
import com.medicalassistance.core.response.AssignedPatientResponse;
import com.medicalassistance.core.response.PatientRecordResponse;
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
    PatientRecordService patientRecordService;

    @Autowired
    AssignedPatientRepository assignedPatientRepository;

    @Autowired
    AssignedPatientMapper assignedPatientMapper;

    @Autowired
    PatientRecordRepository patientRecordRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PatientService patientService;

    public void storeDoctorAppointment(AppointmentRequest appointmentRequest) {
        if (appointmentRequest.getStartDateTime().toInstant().toEpochMilli() <= ((new Date()).getTime() / 1000) ||
                appointmentRequest.getStartDateTime().isAfter(appointmentRequest.getEndDateTime()) ||
                appointmentRequest.getStartDateTime().isEqual(appointmentRequest.getEndDateTime())
        ) {
            throw new InvalidUserRequestException("appointment time period invalid");
        }
        if (patientRecordRepository.existsByPatientRecordId(appointmentRequest.getPatientRecordId())) {
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
        throw new ResourceNotFoundException(String.format("patient record %s not found", appointmentRequest.getPatientRecordId()));
    }

    public Page<AppointmentResponse> getDoctorAppointments(Pageable pageable) {
        User user = userCommonService.getUser();

        Page<DoctorAppointment> pages = appointmentRepository.findByDoctorIdAndStartDateTimeGreaterThanEqual(user.getUserId(), ZonedDateTime.now(), pageable);

        return pages.map(appointmentMapper::toAppointmentResponse);
    }

    public Page<AssignedPatientResponse> getAssignedPatients(Pageable pageable) {
        User user = userCommonService.getUser();

        Page<AssignedPatient> assignedPatientPage = assignedPatientRepository.findByDoctorRegistrationNumber(user.getRegistrationNumber(), pageable);

        return assignedPatientPage.map(assignedPatientMapper::toAssignedPatientResponse);
    }

    public PatientRecordResponse getActivePatient(String patientRecordId) {
        PatientRecord patientRecord = patientRecordRepository.findByPatientRecordId(patientRecordId);
        PatientRecordResponse response = new PatientRecordResponse();
        response.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(patientRecord.getPatientId())));
        response.setRecordId(patientRecordId);
        response.setCreatedAt(patientRecord.getCreatedAt());
        response.setAssessmentResult(patientService.getAssessmentResult(patientRecord.getAssessmentResultId()));
        return response;
    }

    public void rejectPatient(String patientRecordId) {
        PatientRecord patientRecord = patientRecordRepository.findByPatientRecordId(patientRecordId);
        if (patientRecord != null) {
            assignedPatientRepository.deleteById(patientRecord.getAssignedPatientId());
            patientRecordService.afterRejectingPatient(patientRecord, PatientRecordStatus.DOCTOR_REJECTED);
        }
        throw new ResourceNotFoundException("patient record not found");
    }
}
