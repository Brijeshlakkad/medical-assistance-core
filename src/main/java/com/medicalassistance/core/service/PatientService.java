package com.medicalassistance.core.service;

import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.ActivePatient;
import com.medicalassistance.core.entity.AssessmentResult;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.ActivePatientRepository;
import com.medicalassistance.core.repository.AssessmentResultRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PatientRecordCardListResponse getActivePatients() {
        List<ActivePatient> activePatients = activePatientRepository.findAll();

        PatientRecordCardListResponse response = new PatientRecordCardListResponse();
        for (ActivePatient activePatient : activePatients) {
            PatientRecordCardResponse cardResponse = new PatientRecordCardResponse();
            cardResponse.setPatientRecordId(activePatient.getActivePatientId());
            cardResponse.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(activePatient.getPatientRecord().getPatientId())));
            cardResponse.setAssessmentCreatedAt(activePatient.getCreatedAt());
            response.addPatientRecordCardResponse(cardResponse);
        }

        return response;
    }

    public PatientRecordResponse getActivePatient(String activePatientId) {
        ActivePatient activePatient = activePatientRepository.findByActivePatientId(activePatientId);
        PatientRecordResponse response = new PatientRecordResponse();
        response.setPatient(userMapper.toUserCardResponse(userRepository.findByUserId(activePatient.getActivePatientId())));
        response.setRecordId(activePatientId);
        response.setCreatedAt(activePatient.getCreatedAt());
        AssessmentResultResponse assessmentResultResponse = new AssessmentResultResponse();

        AssessmentResult assessmentResult = assessmentResultRepository.findByAssessmentId(activePatient.getPatientRecord().getAssessmentResultId());
        assessmentResultResponse.setAttemptedQuestions(assessmentResult.getAttemptedQuestions());
        response.setAssessmentResult(assessmentResultResponse);
        return response;
    }
}
