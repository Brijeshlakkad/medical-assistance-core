# Details

Date : 2022-11-28 14:01:43

Directory /Users/brijeshlakkad/Documents/Concordia Code/Fall 2022/SOEN 6841 Software Project Management/Projects/medical-assistance-core/src/main/java/com/medicalassistance

Total : 98 files,  4062 codes, 131 comments, 1150 blanks, all 5343 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/main/java/com/medicalassistance/core/CoreApplication.java](/src/main/java/com/medicalassistance/core/CoreApplication.java) | Java | 10 | 0 | 5 | 15 |
| [src/main/java/com/medicalassistance/core/common/AuthorityName.java](/src/main/java/com/medicalassistance/core/common/AuthorityName.java) | Java | 4 | 0 | 3 | 7 |
| [src/main/java/com/medicalassistance/core/common/PatientRecordStatus.java](/src/main/java/com/medicalassistance/core/common/PatientRecordStatus.java) | Java | 17 | 0 | 4 | 21 |
| [src/main/java/com/medicalassistance/core/common/UserCommonService.java](/src/main/java/com/medicalassistance/core/common/UserCommonService.java) | Java | 23 | 0 | 5 | 28 |
| [src/main/java/com/medicalassistance/core/config/CORSConfig.java](/src/main/java/com/medicalassistance/core/config/CORSConfig.java) | Java | 22 | 6 | 3 | 31 |
| [src/main/java/com/medicalassistance/core/config/Converters.java](/src/main/java/com/medicalassistance/core/config/Converters.java) | Java | 18 | 0 | 3 | 21 |
| [src/main/java/com/medicalassistance/core/config/WebSecurityConfig.java](/src/main/java/com/medicalassistance/core/config/WebSecurityConfig.java) | Java | 62 | 26 | 10 | 98 |
| [src/main/java/com/medicalassistance/core/controller/AdminController.java](/src/main/java/com/medicalassistance/core/controller/AdminController.java) | Java | 99 | 0 | 21 | 120 |
| [src/main/java/com/medicalassistance/core/controller/CounselorController.java](/src/main/java/com/medicalassistance/core/controller/CounselorController.java) | Java | 90 | 0 | 20 | 110 |
| [src/main/java/com/medicalassistance/core/controller/DoctorController.java](/src/main/java/com/medicalassistance/core/controller/DoctorController.java) | Java | 77 | 0 | 18 | 95 |
| [src/main/java/com/medicalassistance/core/controller/PatientController.java](/src/main/java/com/medicalassistance/core/controller/PatientController.java) | Java | 60 | 0 | 15 | 75 |
| [src/main/java/com/medicalassistance/core/converter/ZonedDateTimeReadConverter.java](/src/main/java/com/medicalassistance/core/converter/ZonedDateTimeReadConverter.java) | Java | 15 | 0 | 3 | 18 |
| [src/main/java/com/medicalassistance/core/converter/ZonedDateTimeWriteConverter.java](/src/main/java/com/medicalassistance/core/converter/ZonedDateTimeWriteConverter.java) | Java | 13 | 0 | 3 | 16 |
| [src/main/java/com/medicalassistance/core/entity/ActivePatient.java](/src/main/java/com/medicalassistance/core/entity/ActivePatient.java) | Java | 31 | 3 | 11 | 45 |
| [src/main/java/com/medicalassistance/core/entity/Appointment.java](/src/main/java/com/medicalassistance/core/entity/Appointment.java) | Java | 36 | 3 | 15 | 54 |
| [src/main/java/com/medicalassistance/core/entity/Assessment.java](/src/main/java/com/medicalassistance/core/entity/Assessment.java) | Java | 22 | 0 | 10 | 32 |
| [src/main/java/com/medicalassistance/core/entity/AssessmentResult.java](/src/main/java/com/medicalassistance/core/entity/AssessmentResult.java) | Java | 36 | 0 | 15 | 51 |
| [src/main/java/com/medicalassistance/core/entity/AssignedPatient.java](/src/main/java/com/medicalassistance/core/entity/AssignedPatient.java) | Java | 45 | 3 | 17 | 65 |
| [src/main/java/com/medicalassistance/core/entity/AttemptedQuestion.java](/src/main/java/com/medicalassistance/core/entity/AttemptedQuestion.java) | Java | 21 | 0 | 8 | 29 |
| [src/main/java/com/medicalassistance/core/entity/BooleanQuestion.java](/src/main/java/com/medicalassistance/core/entity/BooleanQuestion.java) | Java | 21 | 0 | 8 | 29 |
| [src/main/java/com/medicalassistance/core/entity/CounselorAppointment.java](/src/main/java/com/medicalassistance/core/entity/CounselorAppointment.java) | Java | 12 | 3 | 5 | 20 |
| [src/main/java/com/medicalassistance/core/entity/DateDomainObject.java](/src/main/java/com/medicalassistance/core/entity/DateDomainObject.java) | Java | 26 | 3 | 11 | 40 |
| [src/main/java/com/medicalassistance/core/entity/DoctorAppointment.java](/src/main/java/com/medicalassistance/core/entity/DoctorAppointment.java) | Java | 12 | 3 | 5 | 20 |
| [src/main/java/com/medicalassistance/core/entity/PatientRecord.java](/src/main/java/com/medicalassistance/core/entity/PatientRecord.java) | Java | 59 | 3 | 22 | 84 |
| [src/main/java/com/medicalassistance/core/entity/User.java](/src/main/java/com/medicalassistance/core/entity/User.java) | Java | 121 | 0 | 47 | 168 |
| [src/main/java/com/medicalassistance/core/exception/AlreadyExistsException.java](/src/main/java/com/medicalassistance/core/exception/AlreadyExistsException.java) | Java | 15 | 0 | 4 | 19 |
| [src/main/java/com/medicalassistance/core/exception/ExceptionHandlerControllerAdvice.java](/src/main/java/com/medicalassistance/core/exception/ExceptionHandlerControllerAdvice.java) | Java | 82 | 0 | 16 | 98 |
| [src/main/java/com/medicalassistance/core/exception/InvalidUserRequestException.java](/src/main/java/com/medicalassistance/core/exception/InvalidUserRequestException.java) | Java | 15 | 0 | 4 | 19 |
| [src/main/java/com/medicalassistance/core/exception/ResourceNotFoundException.java](/src/main/java/com/medicalassistance/core/exception/ResourceNotFoundException.java) | Java | 15 | 0 | 5 | 20 |
| [src/main/java/com/medicalassistance/core/mapper/ActivePatientMapper.java](/src/main/java/com/medicalassistance/core/mapper/ActivePatientMapper.java) | Java | 20 | 0 | 5 | 25 |
| [src/main/java/com/medicalassistance/core/mapper/AppointmentMapper.java](/src/main/java/com/medicalassistance/core/mapper/AppointmentMapper.java) | Java | 55 | 0 | 11 | 66 |
| [src/main/java/com/medicalassistance/core/mapper/AssignedPatientMapper.java](/src/main/java/com/medicalassistance/core/mapper/AssignedPatientMapper.java) | Java | 39 | 0 | 10 | 49 |
| [src/main/java/com/medicalassistance/core/mapper/UserMapper.java](/src/main/java/com/medicalassistance/core/mapper/UserMapper.java) | Java | 122 | 0 | 21 | 143 |
| [src/main/java/com/medicalassistance/core/repository/ActivePatientRepository.java](/src/main/java/com/medicalassistance/core/repository/ActivePatientRepository.java) | Java | 16 | 0 | 10 | 26 |
| [src/main/java/com/medicalassistance/core/repository/AssessmentRepository.java](/src/main/java/com/medicalassistance/core/repository/AssessmentRepository.java) | Java | 6 | 0 | 2 | 8 |
| [src/main/java/com/medicalassistance/core/repository/AssessmentResultRepository.java](/src/main/java/com/medicalassistance/core/repository/AssessmentResultRepository.java) | Java | 8 | 0 | 4 | 12 |
| [src/main/java/com/medicalassistance/core/repository/AssignedPatientRepository.java](/src/main/java/com/medicalassistance/core/repository/AssignedPatientRepository.java) | Java | 14 | 0 | 8 | 22 |
| [src/main/java/com/medicalassistance/core/repository/BooleanQuestionRepository.java](/src/main/java/com/medicalassistance/core/repository/BooleanQuestionRepository.java) | Java | 6 | 0 | 2 | 8 |
| [src/main/java/com/medicalassistance/core/repository/ClosedPatientRepository.java](/src/main/java/com/medicalassistance/core/repository/ClosedPatientRepository.java) | Java | 5 | 0 | 2 | 7 |
| [src/main/java/com/medicalassistance/core/repository/CounselorAppointmentRepository.java](/src/main/java/com/medicalassistance/core/repository/CounselorAppointmentRepository.java) | Java | 21 | 0 | 13 | 34 |
| [src/main/java/com/medicalassistance/core/repository/DoctorAppointmentRepository.java](/src/main/java/com/medicalassistance/core/repository/DoctorAppointmentRepository.java) | Java | 22 | 0 | 12 | 34 |
| [src/main/java/com/medicalassistance/core/repository/PatientRecordRepository.java](/src/main/java/com/medicalassistance/core/repository/PatientRecordRepository.java) | Java | 8 | 0 | 4 | 12 |
| [src/main/java/com/medicalassistance/core/repository/UserRepository.java](/src/main/java/com/medicalassistance/core/repository/UserRepository.java) | Java | 23 | 0 | 13 | 36 |
| [src/main/java/com/medicalassistance/core/request/AppointmentListForDateRequest.java](/src/main/java/com/medicalassistance/core/request/AppointmentListForDateRequest.java) | Java | 11 | 0 | 4 | 15 |
| [src/main/java/com/medicalassistance/core/request/AppointmentRequest.java](/src/main/java/com/medicalassistance/core/request/AppointmentRequest.java) | Java | 25 | 0 | 10 | 35 |
| [src/main/java/com/medicalassistance/core/request/AssessmentResultRequest.java](/src/main/java/com/medicalassistance/core/request/AssessmentResultRequest.java) | Java | 11 | 0 | 5 | 16 |
| [src/main/java/com/medicalassistance/core/request/AttemptedQuestionRequest.java](/src/main/java/com/medicalassistance/core/request/AttemptedQuestionRequest.java) | Java | 23 | 0 | 8 | 31 |
| [src/main/java/com/medicalassistance/core/request/DoctorAssignmentRequest.java](/src/main/java/com/medicalassistance/core/request/DoctorAssignmentRequest.java) | Java | 17 | 0 | 6 | 23 |
| [src/main/java/com/medicalassistance/core/request/DummyUsers.java](/src/main/java/com/medicalassistance/core/request/DummyUsers.java) | Java | 60 | 0 | 25 | 85 |
| [src/main/java/com/medicalassistance/core/request/LoginRequest.java](/src/main/java/com/medicalassistance/core/request/LoginRequest.java) | Java | 20 | 0 | 6 | 26 |
| [src/main/java/com/medicalassistance/core/request/UpdatePasswordRequest.java](/src/main/java/com/medicalassistance/core/request/UpdatePasswordRequest.java) | Java | 10 | 0 | 4 | 14 |
| [src/main/java/com/medicalassistance/core/request/UserRequest.java](/src/main/java/com/medicalassistance/core/request/UserRequest.java) | Java | 81 | 0 | 35 | 116 |
| [src/main/java/com/medicalassistance/core/request/UserUpdateRequest.java](/src/main/java/com/medicalassistance/core/request/UserUpdateRequest.java) | Java | 60 | 0 | 26 | 86 |
| [src/main/java/com/medicalassistance/core/response/AdminCounselorCard.java](/src/main/java/com/medicalassistance/core/response/AdminCounselorCard.java) | Java | 17 | 0 | 5 | 22 |
| [src/main/java/com/medicalassistance/core/response/AdminDoctorCard.java](/src/main/java/com/medicalassistance/core/response/AdminDoctorCard.java) | Java | 17 | 0 | 5 | 22 |
| [src/main/java/com/medicalassistance/core/response/AdminPatientCard.java](/src/main/java/com/medicalassistance/core/response/AdminPatientCard.java) | Java | 17 | 0 | 5 | 22 |
| [src/main/java/com/medicalassistance/core/response/AdminPatientReport.java](/src/main/java/com/medicalassistance/core/response/AdminPatientReport.java) | Java | 11 | 0 | 5 | 16 |
| [src/main/java/com/medicalassistance/core/response/AdminPatientReportParameters.java](/src/main/java/com/medicalassistance/core/response/AdminPatientReportParameters.java) | Java | 38 | 0 | 16 | 54 |
| [src/main/java/com/medicalassistance/core/response/AdminUserCreateResponse.java](/src/main/java/com/medicalassistance/core/response/AdminUserCreateResponse.java) | Java | 24 | 0 | 7 | 31 |
| [src/main/java/com/medicalassistance/core/response/AppointmentListForDateResponse.java](/src/main/java/com/medicalassistance/core/response/AppointmentListForDateResponse.java) | Java | 18 | 0 | 8 | 26 |
| [src/main/java/com/medicalassistance/core/response/AppointmentResponse.java](/src/main/java/com/medicalassistance/core/response/AppointmentResponse.java) | Java | 39 | 0 | 17 | 56 |
| [src/main/java/com/medicalassistance/core/response/AssessmentResponse.java](/src/main/java/com/medicalassistance/core/response/AssessmentResponse.java) | Java | 12 | 0 | 5 | 17 |
| [src/main/java/com/medicalassistance/core/response/AssessmentResultResponse.java](/src/main/java/com/medicalassistance/core/response/AssessmentResultResponse.java) | Java | 14 | 0 | 6 | 20 |
| [src/main/java/com/medicalassistance/core/response/AssignedPatientResponse.java](/src/main/java/com/medicalassistance/core/response/AssignedPatientResponse.java) | Java | 39 | 0 | 17 | 56 |
| [src/main/java/com/medicalassistance/core/response/AttemptedQuestionResponse.java](/src/main/java/com/medicalassistance/core/response/AttemptedQuestionResponse.java) | Java | 21 | 0 | 7 | 28 |
| [src/main/java/com/medicalassistance/core/response/BooleanQuestionProjection.java](/src/main/java/com/medicalassistance/core/response/BooleanQuestionProjection.java) | Java | 22 | 0 | 8 | 30 |
| [src/main/java/com/medicalassistance/core/response/CounselorDoctorCardResponse.java](/src/main/java/com/medicalassistance/core/response/CounselorDoctorCardResponse.java) | Java | 38 | 0 | 16 | 54 |
| [src/main/java/com/medicalassistance/core/response/ErrorResponse.java](/src/main/java/com/medicalassistance/core/response/ErrorResponse.java) | Java | 26 | 3 | 9 | 38 |
| [src/main/java/com/medicalassistance/core/response/ExceptionResponse.java](/src/main/java/com/medicalassistance/core/response/ExceptionResponse.java) | Java | 17 | 0 | 6 | 23 |
| [src/main/java/com/medicalassistance/core/response/LoginResponse.java](/src/main/java/com/medicalassistance/core/response/LoginResponse.java) | Java | 40 | 6 | 13 | 59 |
| [src/main/java/com/medicalassistance/core/response/PatientRecordCardResponse.java](/src/main/java/com/medicalassistance/core/response/PatientRecordCardResponse.java) | Java | 25 | 0 | 9 | 34 |
| [src/main/java/com/medicalassistance/core/response/PatientRecordResponse.java](/src/main/java/com/medicalassistance/core/response/PatientRecordResponse.java) | Java | 32 | 0 | 11 | 43 |
| [src/main/java/com/medicalassistance/core/response/PatientRecordStatusResponse.java](/src/main/java/com/medicalassistance/core/response/PatientRecordStatusResponse.java) | Java | 45 | 0 | 15 | 60 |
| [src/main/java/com/medicalassistance/core/response/UpdatePasswordResponse.java](/src/main/java/com/medicalassistance/core/response/UpdatePasswordResponse.java) | Java | 20 | 0 | 7 | 27 |
| [src/main/java/com/medicalassistance/core/response/UserCardResponse.java](/src/main/java/com/medicalassistance/core/response/UserCardResponse.java) | Java | 31 | 0 | 13 | 44 |
| [src/main/java/com/medicalassistance/core/response/UserProfileResponse.java](/src/main/java/com/medicalassistance/core/response/UserProfileResponse.java) | Java | 67 | 0 | 29 | 96 |
| [src/main/java/com/medicalassistance/core/response/UserResponse.java](/src/main/java/com/medicalassistance/core/response/UserResponse.java) | Java | 59 | 0 | 25 | 84 |
| [src/main/java/com/medicalassistance/core/response/projection/UserProjection.java](/src/main/java/com/medicalassistance/core/response/projection/UserProjection.java) | Java | 8 | 0 | 4 | 12 |
| [src/main/java/com/medicalassistance/core/security/JwtAuthenticationEntryPoint.java](/src/main/java/com/medicalassistance/core/security/JwtAuthenticationEntryPoint.java) | Java | 17 | 4 | 6 | 27 |
| [src/main/java/com/medicalassistance/core/security/JwtAuthorizationTokenFilter.java](/src/main/java/com/medicalassistance/core/security/JwtAuthorizationTokenFilter.java) | Java | 87 | 6 | 19 | 112 |
| [src/main/java/com/medicalassistance/core/security/JwtRequestFilter.java](/src/main/java/com/medicalassistance/core/security/JwtRequestFilter.java) | Java | 51 | 8 | 12 | 71 |
| [src/main/java/com/medicalassistance/core/security/JwtTokenUtil.java](/src/main/java/com/medicalassistance/core/security/JwtTokenUtil.java) | Java | 134 | 2 | 32 | 168 |
| [src/main/java/com/medicalassistance/core/security/JwtUser.java](/src/main/java/com/medicalassistance/core/security/JwtUser.java) | Java | 61 | 3 | 14 | 78 |
| [src/main/java/com/medicalassistance/core/security/JwtUserDetailService.java](/src/main/java/com/medicalassistance/core/security/JwtUserDetailService.java) | Java | 23 | 0 | 5 | 28 |
| [src/main/java/com/medicalassistance/core/security/JwtUserFactory.java](/src/main/java/com/medicalassistance/core/security/JwtUserFactory.java) | Java | 20 | 0 | 7 | 27 |
| [src/main/java/com/medicalassistance/core/service/AdminService.java](/src/main/java/com/medicalassistance/core/service/AdminService.java) | Java | 290 | 11 | 49 | 350 |
| [src/main/java/com/medicalassistance/core/service/AssessmentService.java](/src/main/java/com/medicalassistance/core/service/AssessmentService.java) | Java | 60 | 2 | 17 | 79 |
| [src/main/java/com/medicalassistance/core/service/BaseService.java](/src/main/java/com/medicalassistance/core/service/BaseService.java) | Java | 159 | 3 | 23 | 185 |
| [src/main/java/com/medicalassistance/core/service/CounselorService.java](/src/main/java/com/medicalassistance/core/service/CounselorService.java) | Java | 148 | 7 | 30 | 185 |
| [src/main/java/com/medicalassistance/core/service/DoctorService.java](/src/main/java/com/medicalassistance/core/service/DoctorService.java) | Java | 126 | 3 | 26 | 155 |
| [src/main/java/com/medicalassistance/core/service/PatientRecordService.java](/src/main/java/com/medicalassistance/core/service/PatientRecordService.java) | Java | 57 | 5 | 16 | 78 |
| [src/main/java/com/medicalassistance/core/service/PatientService.java](/src/main/java/com/medicalassistance/core/service/PatientService.java) | Java | 80 | 0 | 18 | 98 |
| [src/main/java/com/medicalassistance/core/service/UserService.java](/src/main/java/com/medicalassistance/core/service/UserService.java) | Java | 26 | 0 | 7 | 33 |
| [src/main/java/com/medicalassistance/core/util/CaseInsensitiveRequestFilter.java](/src/main/java/com/medicalassistance/core/util/CaseInsensitiveRequestFilter.java) | Java | 56 | 7 | 10 | 73 |
| [src/main/java/com/medicalassistance/core/util/EncryptionUtil.java](/src/main/java/com/medicalassistance/core/util/EncryptionUtil.java) | Java | 21 | 0 | 7 | 28 |
| [src/main/java/com/medicalassistance/core/util/PHPass.java](/src/main/java/com/medicalassistance/core/util/PHPass.java) | Java | 148 | 2 | 14 | 164 |
| [src/main/java/com/medicalassistance/core/util/TimeUtil.java](/src/main/java/com/medicalassistance/core/util/TimeUtil.java) | Java | 14 | 6 | 4 | 24 |
| [src/main/java/com/medicalassistance/core/util/UserUtil.java](/src/main/java/com/medicalassistance/core/util/UserUtil.java) | Java | 25 | 0 | 4 | 29 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)