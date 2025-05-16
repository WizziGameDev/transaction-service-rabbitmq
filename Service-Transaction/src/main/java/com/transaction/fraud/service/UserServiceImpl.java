package com.transaction.fraud.service;

import com.transaction.fraud.dto.UserRequest;
import com.transaction.fraud.dto.UserResponse;
import com.transaction.fraud.entity.User;
import com.transaction.fraud.exception.UserException;
import com.transaction.fraud.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAllByDeletedFalse()
                .stream()
                .map(data -> new UserResponse(data.getId(), data.getName(), data.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Integer id) {

        User user = userRepository.findFirstByIdAndDeleted(id, false)
                .orElseThrow(() -> new UserException("User Not Found"));

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreatedAt(Instant.now().getEpochSecond());
        user.setUpdatedAt(0L);
        user.setDeleted(false);

        User save = userRepository.save(user);

        return UserResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .email(save.getEmail())
                .build();
    }

    @Override
    @Transactional
    public UserResponse updateUser(Integer Id, UserRequest userRequest) {

        User user = userRepository.findFirstByIdAndDeleted(Id, false).orElseThrow(() -> new UserException("User Not Found"));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUpdatedAt(Instant.now().getEpochSecond());

        User updateUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updateUser.getId())
                .name(updateUser.getName())
                .email(updateUser.getEmail())
                .build();
    }

    @Override
    @Transactional
    public String deleteUser(Integer Id) {

        User user = userRepository.findFirstByIdAndDeleted(Id, false).orElseThrow(() -> new UserException("User Not Found"));

        user.setDeleted(true);
        userRepository.save(user);

        return "Successfully deleted user";
    }
}
