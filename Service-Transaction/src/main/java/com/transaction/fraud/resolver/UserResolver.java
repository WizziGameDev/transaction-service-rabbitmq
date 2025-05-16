package com.transaction.fraud.resolver;

import com.transaction.fraud.dto.UserRequest;
import com.transaction.fraud.dto.UserResponse;
import com.transaction.fraud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class UserResolver {

    @Autowired
    private UserService userService;

    private final ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @QueryMapping(name = "users")
    public List<UserResponse> getUsers() {
        return CompletableFuture.supplyAsync(userService::getUsers, virtualExecutor).join();
    }

    @QueryMapping(name = "userById")
    public UserResponse getUserById(@Argument Integer id) {
        return CompletableFuture.supplyAsync(() -> userService.getUserById(id), virtualExecutor).join();
    }

    @MutationMapping(name = "createUser")
    public UserResponse createUser(@Argument("request") UserRequest userRequest) {
        return CompletableFuture.supplyAsync(() -> userService.createUser(userRequest), virtualExecutor).join();
    }

    @MutationMapping(name = "updateUser")
    public UserResponse updateUser(@Argument Integer id, @Argument("request") UserRequest userRequest) {
        return CompletableFuture.supplyAsync(() -> userService.updateUser(id, userRequest), virtualExecutor).join();
    }

    @MutationMapping(name = "deleteUser")
    public String deleteUser(@Argument Integer id) {
        return CompletableFuture.supplyAsync(() -> userService.deleteUser(id), virtualExecutor).join();
    }
}
