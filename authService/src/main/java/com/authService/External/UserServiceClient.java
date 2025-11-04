package com.authService.External;

import com.authService.Entity.User;
import com.common.dto.auth.AuthRequest;
import com.common.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",url = "${user-service.url}")
public interface UserServiceClient {

    @PostMapping("/user/auth/register")
    UserResponse register(@RequestBody AuthRequest user);
    @GetMapping("/user/auth/{username}")
    User fetchByUsername(@PathVariable String username);
}
