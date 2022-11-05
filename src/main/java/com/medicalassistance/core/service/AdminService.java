package com.medicalassistance.core.service;

import com.medicalassistance.core.entity.Assessment;
import com.medicalassistance.core.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AssessmentRepository assessmentRepository;

    // admin
    public void createAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
    }
}
