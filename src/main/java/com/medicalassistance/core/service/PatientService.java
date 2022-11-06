package com.medicalassistance.core.service;

import com.medicalassistance.core.common.PatientRecordStatus;
import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.*;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.*;
import com.medicalassistance.core.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    ActivePatientRepository activePatientRepository;

    @Autowired
    AssessmentResultRepository assessmentResultRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserCommonService userCommonService;

    @Autowired
    BooleanQuestionRepository booleanQuestionRepository;

    @Autowired
    PatientRecordRepository patientRecordRepository;

    @Autowired
    CounselorAppointmentRepository counselorAppointmentRepository;

    @Autowired
    DoctorAppointmentRepository doctorAppointmentRepository;

    public PatientRecordCardListResponse getActivePatients() {
        List<ActivePatient> activePatients = activePatientRepository.findAll();

        PatientRecordCardListResponse response = new PatientRecordCardListResponse();
        for (ActivePatient activePatient : activePatients) {
            PatientRecordCardResponse cardResponse = new PatientRecordCardResponse();
            cardResponse.setPatientRecordId(activePatient.getActivePatientId());
            cardResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(activePatient.getPatientId())));
            cardResponse.setAssessmentCreatedAt(activePatient.getCreatedAt());
            response.addPatientRecordCardResponse(cardResponse);
        }

        return response;
    }

    public PatientRecordResponse getActivePatient(String activePatientId) {
        ActivePatient activePatient = activePatientRepository.findByActivePatientId(activePatientId);
        PatientRecordResponse response = new PatientRecordResponse();
        response.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(activePatient.getPatientId())));
        response.setRecordId(activePatientId);
        response.setCreatedAt(activePatient.getCreatedAt());

        AssessmentResultResponse assessmentResultResponse = new AssessmentResultResponse();
        AssessmentResult assessmentResult = assessmentResultRepository.findByAssessmentResultId(activePatient.getAssessmentResultId());
        List<AttemptedQuestionResponse> attemptedQuestionResponses = new ArrayList<>();
        for (AttemptedQuestion attemptedQuestion : assessmentResult.getAttemptedQuestions()) {
            attemptedQuestionResponses.add(new AttemptedQuestionResponse(
                    booleanQuestionRepository.findByQuestionId(attemptedQuestion.getQuestionId()).getQuestion(),
                    attemptedQuestion.getAnswer()
            ));
        }
        assessmentResultResponse.setAttemptedQuestions(attemptedQuestionResponses);
        response.setAssessmentResult(assessmentResultResponse);
        return response;
    }

    public PatientRecordStatusResponse getPatientRecordStatus() {
        String patientId = userCommonService.getUser().getUserId();
        ActivePatient activePatient = activePatientRepository.findByPatientId(patientId);
        if (activePatient == null) {
            return new PatientRecordStatusResponse(PatientRecordStatus.NULL);
        }
        PatientRecord patientRecord = patientRecordRepository.findByPatientRecordId(activePatient.getPatientRecordId());
        PatientRecordStatusResponse patientRecordStatusResponse = new PatientRecordStatusResponse();
        patientRecordStatusResponse.setPatientRecordStatus(patientRecord.getStatus());
        patientRecordStatusResponse.setCreatedAt(patientRecord.getCreatedAt());
        patientRecordStatusResponse.setUpdatedAt(patientRecord.getUpdatedAt());

        if (patientRecord.getStatus() == PatientRecordStatus.COUNSELOR_APPOINTMENT) {
            CounselorAppointment appointment = counselorAppointmentRepository.findByAppointmentId(patientRecord.getRelatedKey());
            patientRecordStatusResponse.setStartDateTime(appointment.getStartDateTime());
            patientRecordStatusResponse.setEndDateTime(appointment.getEndDateTime());
        }
        if (patientRecord.getStatus() == PatientRecordStatus.DOCTOR_APPOINTMENT) {
            DoctorAppointment appointment = doctorAppointmentRepository.findByAppointmentId(patientRecord.getRelatedKey());
            patientRecordStatusResponse.setStartDateTime(appointment.getStartDateTime());
            patientRecordStatusResponse.setEndDateTime(appointment.getEndDateTime());
        }
        return patientRecordStatusResponse;
    }
}
