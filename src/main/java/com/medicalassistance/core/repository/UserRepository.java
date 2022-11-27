package com.medicalassistance.core.repository;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.response.CounselorDoctorCardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(String userId);

    User findByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByUserId(String userId);

    Page<User> findByAuthoritiesContainsOrderByModifiedAt(AuthorityName authorities, Pageable pageable);

    Page<User> findByAuthoritiesContainsAndCreatedAtBetweenOrderByCreatedAt(Set<AuthorityName> authorities, Date createdAt, Date createdAt2, Pageable pageable);

    boolean existsByRegistrationNumber(String registrationNumber);

    CounselorDoctorCardResponse findFirstByRegistrationNumber(String registrationNumber);
}