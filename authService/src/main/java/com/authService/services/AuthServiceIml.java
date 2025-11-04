package com.authService.services;

import com.authService.Entity.User;
import com.authService.External.UserServiceClient;
import com.common.security.JwtUtil;
import com.common.dto.auth.AuthRequest;
import com.common.dto.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceIml implements AuthService {

    @Autowired
    private  UserServiceClient client;
    @Autowired
    private  JwtUtil jwt;
    @Autowired
    private  AuthenticationManager mgr;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserResponse register(AuthRequest u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return client.register(u);
    }

    @Override
    public String login(AuthRequest creds) {
        try {
            String u = creds.getUsername(), p = creds.getPassword();
            User user = client.fetchByUsername(u);
            if (user != null) {
                List<String>  roles=new ArrayList<>();
                roles.add("USER");
                return jwt.generateToken(u,roles);
            }
          throw  new RuntimeException("user not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
