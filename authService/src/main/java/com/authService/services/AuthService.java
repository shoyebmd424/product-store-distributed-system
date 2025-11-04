package com.authService.services;

import com.authService.Entity.User;
import com.common.dto.auth.AuthRequest;
import com.common.dto.user.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    UserResponse register(AuthRequest request);

    String login(AuthRequest request);
}
