package com.medicalassistance.core.repository;

import com.medicalassistance.core.common.AuthorityName;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.response.CounselorDoctorCardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserIdAndDeletedFalse(String userId);

    User findByEmailAddressAndDeletedFalse(String emailAddress);

    User findByEmailAddressAndAuthoritiesContainsAndDeletedFalse(String emailAddress, Set<AuthorityName> authorityNames);

    boolean existsByEmailAddressAndDeletedFalse(String emailAddress);

    boolean existsByUserIdAndDeletedFalse(String userId);

    Page<User> findByAuthoritiesContainsAndDeletedFalseOrderByModifiedAt(AuthorityName authorities, Pageable pageable);

    List<User> findByAuthoritiesContainsAndCreatedAtBetweenAndDeletedFalseOrderByCreatedAt(Set<AuthorityName> authorities, Date createdAt, Date createdAt2);

    List<User> findByAuthoritiesContainsAndCreatedAtAndDeletedFalseOrderByCreatedAt(Set<AuthorityName> authorities, Date createdAt);

    Integer countByAuthoritiesContains(Set<AuthorityName> authorities);

    boolean existsByRegistrationNumberAndDeletedFalse(String registrationNumber);

    CounselorDoctorCardResponse findFirstByRegistrationNumberAndDeletedFalse(String registrationNumber);
}