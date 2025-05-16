package com.transaction.fraud.service;

import com.transaction.fraud.dto.UserRequest;
import com.transaction.fraud.dto.UserResponse;

import java.util.List;

public interface UserService {

    // Get users
    List<UserResponse> getUsers();

    // Get user by Id
    UserResponse getUserById(Integer id);

    // Create user
    UserResponse createUser(UserRequest userRequest);

    // Update user]
    UserResponse updateUser(Integer Id, UserRequest userRequest);

    // Delete user
    String deleteUser(Integer Id);
}
