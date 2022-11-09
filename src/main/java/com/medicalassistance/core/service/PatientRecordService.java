package com.medicalassistance.core.service;

import com.medicalassistance.core.common.PatientRecordStatus;
import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.*;
import com.medicalassistance.core.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientRecordService {
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

    @Autowired
    PatientRecordRepository patientRecordRepository;

    public PatientRecord afterAssessment(AssessmentResult assessmentResult) {
        // store the patient record
        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setAssessmentResultId(assessmentResult.getAssessmentResultId());
        patientRecord.setPatientId(assessmentResult.getPatientId());
        patientRecord.setStatus(PatientRecordStatus.COUNSELOR_IN_PROGRESS);
        patientRecord = patientRecordRepository.save(patientRecord);

        // create an active patient record
        ActivePatient activePatient = new ActivePatient();
        activePatient.setPatientId(assessmentResult.getPatientId());
        activePatient.setAssessmentResultId(assessmentResult.getAssessmentResultId());
        activePatient.setPatientRecordId(patientRecord.getPatientRecordId());
        activePatientRepository.save(activePatient);

        return patientRecord;
    }

    public PatientRecord afterAppointment(Appointment appointment, PatientRecordStatus status) {
        if (status == PatientRecordStatus.COUNSELOR_APPOINTMENT || status == PatientRecordStatus.DOCTOR_APPOINTMENT) {
            ActivePatient activePatient = activePatientRepository.findByActivePatientId(appointment.getPatientRecordId());

            // update patient record (ActivePatient)
            PatientRecord patientRecord = patientRecordRepository.findByPatientRecordId(activePatient.getPatientRecordId());
            patientRecord.update();
            patientRecord.setAppointmentId(appointment.getAppointmentId());
            patientRecord.setStatus(status);
            return patientRecordRepository.save(patientRecord);
        }
        return null;
    }

    public PatientRecord afterAssigningDoctor(AssignedPatient assignedPatient, String activePatientId) {
        ActivePatient activePatient = activePatientRepository.findByActivePatientId(activePatientId);

        // delete active patient
        activePatientRepository.delete(activePatient);

        // update patient record
        PatientRecord patientRecord = patientRecordRepository.findByPatientRecordId(activePatient.getPatientRecordId());
        patientRecord.update();
        patientRecord.setAssignedPatientId(assignedPatient.getAssignedPatientId());
        patientRecord.setStatus(PatientRecordStatus.DOCTOR_IN_PROGRESS);
        return patientRecordRepository.save(patientRecord);
    }
}
