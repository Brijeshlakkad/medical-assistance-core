package com.medicalassistance.core.service;

import com.medicalassistance.core.common.UserCommonService;
import com.medicalassistance.core.entity.User;
import com.medicalassistance.core.mapper.UserMapper;
import com.medicalassistance.core.repository.UserRepository;
import com.medicalassistance.core.request.UserUpdateRequest;
import com.medicalassistance.core.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserCommonService userCommonService;

    @Autowired
    UserRepository userRepository;

    public UserResponse getProfileCard() {
        User user = userCommonService.getUser();
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateProfile(UserUpdateRequest userUpdateRequest) {
        User updatedUser = userMapper.fromUserUpdateRequest(userUpdateRequest);
        return userMapper.toUserResponse(userRepository.save(updatedUser));
    }
}
