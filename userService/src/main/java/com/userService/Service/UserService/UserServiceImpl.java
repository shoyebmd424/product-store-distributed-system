package com.userService.Service.UserService;

import com.common.dto.auth.AuthRequest;
import com.common.dto.user.UserResponse;
import com.userService.Entity.User;
import com.userService.Exception.UserAlreadyExistsException;
import com.userService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserResponse registerService(AuthRequest userRequest) {
        User user=new User();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Optional<User> optionalUser= userRepository.findByUsername(userRequest.getUsername());
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User already exist !");
        }
        User userReponse=userRepository.save(user);
        return new UserResponse(userReponse.getId(),userReponse.getUsername(),userReponse.getEmail());
    }

    @Override
    public User getUserByUsernameService(String username) {
        try {
            return userRepository.findByUsername(username).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
