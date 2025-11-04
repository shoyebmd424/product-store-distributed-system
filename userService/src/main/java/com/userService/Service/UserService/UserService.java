package com.userService.Service.UserService;

import com.common.dto.auth.AuthRequest;
import com.common.dto.user.UserResponse;
import com.userService.Entity.User;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    UserResponse registerService(@Valid AuthRequest userRequest);

    User getUserByUsernameService(String username);

    List<User> getAllUsers();
}
