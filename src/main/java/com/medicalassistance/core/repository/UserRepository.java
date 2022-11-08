package com.medicalassistance.core.repository;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.response.CounselorDoctorCardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(String userId);

    User findByEmailAddress(String emailAddress);

    long count();

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByUserId(String userId);

    Page<CounselorDoctorCardResponse> findByAuthoritiesContains(AuthorityName authorities, Pageable pageable);

    boolean existsByRegistrationNumber(String registrationNumber);

    CounselorDoctorCardResponse findFirstByRegistrationNumber(String registrationNumber);
}