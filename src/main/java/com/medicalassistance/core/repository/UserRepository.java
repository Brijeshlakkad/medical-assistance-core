package com.medicalassistance.core.repository;

import com.medicalassistance.core.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(String userId);

    User findByEmailAddress(String emailAddress);

    long count();

    boolean existsByEmailAddress(String emailAddress);
}