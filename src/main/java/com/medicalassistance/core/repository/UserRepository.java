package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{userId:'?0'}")
    User findByUserId(String userId);

    @Query(value = "{emailAddress:'?0'}")
    User findByEmailAddress(String emailAddress);

    long count();

    boolean existsByEmailAddress(String emailAddress);
}