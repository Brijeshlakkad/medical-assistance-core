package com.medicalassistance.core.repository;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.response.DoctorCardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(String userId);

    User findByEmailAddress(String emailAddress);

    long count();

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByUserId(String userId);

    Page<DoctorCardResponse> findByAuthoritiesContains(AuthorityName authorities, Pageable pageable);
}