package com.medicalassistance.core.mapper;


import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.ActivePatient;
import com.medicalassistance.core.entity.AssessmentResult;
import com.medicalassistance.core.entity.AssignedPatient;
import com.medicalassistance.core.entity.PatientRecord;
import com.medicalassistance.core.exception.ResourceNotFoundException;
import com.medicalassistance.core.repository.ActivePatientRepository;
import com.medicalassistance.core.repository.AssessmentResultRepository;
import com.medicalassistance.core.repository.PatientRecordRepository;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.response.AssignedPatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignedPatientMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivePatientRepository activePatientRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCommonService userCommonService;

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    @Autowired
    private AssessmentResultRepository assessmentResultRepository;

    public AssignedPatientResponse toAssignedPatientResponse(AssignedPatient assignedPatient) {
        if (activePatientRepository.existsByActivePatientId(assignedPatient.getActivePatientId())) {
            ActivePatient activePatient = activePatientRepository.findByActivePatientId(assignedPatient.getActivePatientId());
            AssignedPatientResponse appointmentResponse = new AssignedPatientResponse();

            appointmentResponse.setPatientRecordId(assignedPatient.getActivePatientId());
            appointmentResponse.setCounselor(userRepository.findFirstByRegistrationNumber(assignedPatient.getCounselorRegistrationNumber()));
            appointmentResponse.setPatient(userMapper.toPatientCardResponse(userRepository.findByUserId(activePatient.getPatientId())));
            PatientRecord patientRecord = patientRecordRepository.findByPatientRecordId(activePatient.getPatientRecordId());
            AssessmentResult assessmentResult = assessmentResultRepository.findByAssessmentResultId(patientRecord.getAssessmentResultId());
            appointmentResponse.setAssessmentCreatedAt(assessmentResult.getCreatedAt());
            appointmentResponse.setAssignedAt(assignedPatient.getCreatedAt());
            return appointmentResponse;
        }
        throw new ResourceNotFoundException("active patient record doesn't found");
    }
}