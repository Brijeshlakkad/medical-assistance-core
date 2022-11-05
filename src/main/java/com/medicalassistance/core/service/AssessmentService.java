package com.medicalassistance.core.service;

import com.medicalassistance.core.common.ActivePatientRecordStatus;
import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.*;
import com.medicalassistance.core.exception.AlreadyExistsException;
import com.medicalassistance.core.repository.ActivePatientRepository;
import com.medicalassistance.core.repository.AssessmentRepository;
import com.medicalassistance.core.repository.AssessmentResultRepository;
import com.medicalassistance.core.repository.BooleanQuestionRepository;
import com.medicalassistance.core.request.AssessmentResultRequest;
import com.medicalassistance.core.request.AttemptedQuestionRequest;
import com.medicalassistance.core.response.AssessmentResponse;
import com.medicalassistance.core.response.BooleanQuestionProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AssessmentService {
    @Autowired
    AssessmentRepository assessmentRepository;

    @Autowired
    AssessmentResultRepository assessmentResultRepository;

    @Autowired
    ActivePatientRepository activePatientRepository;

    @Autowired
    UserCommonService userCommonService;

    @Autowired
    BooleanQuestionRepository booleanQuestionRepository;

    public AssessmentResponse getAssessment(String assessmentId) {
        AssessmentResponse response = new AssessmentResponse();
        Assessment assessment = assessmentRepository.findByAssessmentId(assessmentId);
        for (String questionId : assessment.getQuestionIds()) {
            response.addQuestion(new BooleanQuestionProjection(booleanQuestionRepository.findByQuestionId(questionId)));
        }
        return response;
    }

    public void storeAssessmentResult(String assessmentId, AssessmentResultRequest assessmentRequest) {
        String userId = userCommonService.getUser().getUserId();

        if (activePatientRepository.existsByPatientRecord_PatientId(userId)) {
            throw new AlreadyExistsException("You already have an active patient file with us!");
        }

        AssessmentResult assessmentResult = new AssessmentResult();

        // store the assessment result
        assessmentResult.setAssessmentId(assessmentId);
        List<AttemptedQuestion> attemptedQuestions = new ArrayList<>();
        for (int i = 0; i < assessmentRequest.getQuestions().size(); i++) {
            AttemptedQuestionRequest questionRequest = assessmentRequest.getQuestions().get(i);
            attemptedQuestions.add(new AttemptedQuestion(questionRequest.getQuestionId(), questionRequest.getAnswer()));
        }
        assessmentResult.setPatientId(userId);
        assessmentResult.setCreatedAt(new Date());
        assessmentResult.setAttemptedQuestions(attemptedQuestions);
        AssessmentResult result = assessmentResultRepository.save(assessmentResult);

        // store the patient record as an active patient
        ActivePatient activePatient = new ActivePatient();
        activePatient.setPatientRecord(new PatientRecord(result.getAssessmentResultId(), userId));
        activePatient.setStatus(ActivePatientRecordStatus.COUNSELOR_IN_PROGRESS);
        activePatientRepository.save(activePatient);
    }
}
