package com.medicalassistance.core.service;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.common.PatientRecordStatus;
import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.AssignedPatient;
import com.medicalassistance.core.entity.CounselorAppointment;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.exception.AlreadyExistsException;
import com.medicalassistance.core.exception.InvalidUserRequestException;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.mapper.AppointmentMapper;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.ActivePatientRepository;
import com.medicalassistance.core.repository.AssignedPatientRepository;
import com.medicalassistance.core.repository.CounselorAppointmentRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.AppointmentRequest;
import com.medicalassistance.core.request.DoctorAssignmentRequest;
import com.medicalassistance.core.response.AppointmentResponse;
import com.medicalassistance.core.response.CounselorDoctorCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CounselorService {
    @Autowired
    UserCommonService userCommonService;

    @Autowired
    CounselorAppointmentRepository appointmentRepository;

    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivePatientRepository activePatientRepository;

    @Autowired
    PatientRecordService patientRecordService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AssignedPatientRepository assignedPatientRepository;

    public void storeCounselorAppointment(AppointmentRequest appointmentRequest) {
        if (appointmentRequest.getStartDateTime().isBefore(ZonedDateTime.now()) ||
                appointmentRequest.getStartDateTime().isEqual(ZonedDateTime.now()) ||
                appointmentRequest.getStartDateTime().isAfter(appointmentRequest.getEndDateTime()) ||
                appointmentRequest.getStartDateTime().isEqual(appointmentRequest.getEndDateTime())) {
            throw new InvalidUserRequestException("appointment time period invalid");
        }
        if (activePatientRepository.existsByActivePatientId(appointmentRequest.getActivePatientId())) {
            if (appointmentRepository.existsByStartDateTimeBetweenOrStartDateTimeEqualsOrStartDateTimeEquals(
                    appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime(),
                    appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime()) ||
                    appointmentRepository.existsByEndDateTimeBetweenOrEndDateTimeEqualsOrEndDateTimeEquals(
                            appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime(),
                            appointmentRequest.getStartDateTime(), appointmentRequest.getEndDateTime())) {
                throw new AlreadyExistsException("conflict: counselor has the reserved time slot during the provided time period");
            }
            // save counselor appointment
            CounselorAppointment counselorAppointment = appointmentMapper.fromAppointmentRequestToCounselorAppointment(appointmentRequest);
            counselorAppointment = appointmentRepository.save(counselorAppointment);

            // update patient record
            patientRecordService.afterAppointment(counselorAppointment, PatientRecordStatus.COUNSELOR_APPOINTMENT);
            return;
        }
        throw new ResourceNotFoundException(String.format("patient record %s not found", appointmentRequest.getActivePatientId()));
    }

    public Page<AppointmentResponse> getCounselorAppointments(Pageable pageable) {
        User user = userCommonService.getUser();

        Page<CounselorAppointment> pages = appointmentRepository.findByCounselorIdAndStartDateTimeGreaterThanEqual(user.getUserId(), ZonedDateTime.now(), pageable);

        return pages.map(appointmentMapper::toAppointmentResponse);
    }

    public Page<CounselorDoctorCardResponse> getDoctorPage(Pageable pageable) {
        Page<CounselorDoctorCardResponse> page = userRepository.findByAuthoritiesContains(AuthorityName.ROLE_DOCTOR, pageable);
        return page.map((CounselorDoctorCardResponse doctorRecord) -> {
            doctorRecord.setCurrentPatients(assignedPatientRepository.countByDoctorRegistrationNumber(doctorRecord.getRegistrationNumber()));
            return doctorRecord;
        });
    }

    public void assignDoctorToPatient(DoctorAssignmentRequest doctorAssignmentRequest) {
        String counselorRegistrationNumber = userCommonService.getUser().getRegistrationNumber();
        if (!activePatientRepository.existsByActivePatientId(doctorAssignmentRequest.getActivePatientId())) {
            throw new ResourceNotFoundException(String.format("active patient record %s not found", doctorAssignmentRequest.getActivePatientId()));
        }
        if (!userRepository.existsByRegistrationNumber(doctorAssignmentRequest.getDoctorRegistrationNumber())) {
            throw new ResourceNotFoundException(String.format("doctor with %s not found", doctorAssignmentRequest.getDoctorRegistrationNumber()));
        }
        // check if the active patient record has already been assigned to a doctor
        if (assignedPatientRepository.existsByActivePatientId(doctorAssignmentRequest.getActivePatientId())) {
            throw new AlreadyExistsException(String.format("active patient record %s is already assigned to a doctor", doctorAssignmentRequest.getActivePatientId()));
        }

        // save assigned patient record
        AssignedPatient assignedPatient = new AssignedPatient();
        assignedPatient.setActivePatientId(doctorAssignmentRequest.getActivePatientId());
        assignedPatient.setDoctorRegistrationNumber(doctorAssignmentRequest.getDoctorRegistrationNumber());
        assignedPatient.setCounselorRegistrationNumber(counselorRegistrationNumber);
        assignedPatient = assignedPatientRepository.save(assignedPatient);

        // update patient record after assigning a doctor to active patient record
        patientRecordService.afterAssigningDoctor(assignedPatient);
    }
}