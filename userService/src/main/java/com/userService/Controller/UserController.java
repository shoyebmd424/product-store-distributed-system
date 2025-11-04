package com.userService.Controller;

import com.common.dto.auth.AuthRequest;
import com.common.dto.user.UserResponse;
import com.userService.Entity.User;
import com.userService.Service.UserService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public  ResponseEntity<UserResponse> register(@RequestBody @Valid AuthRequest userRequest){
        return ResponseEntity.ok(userService.registerService(userRequest));
    }

    @GetMapping("/auth/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsernameService(username));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
