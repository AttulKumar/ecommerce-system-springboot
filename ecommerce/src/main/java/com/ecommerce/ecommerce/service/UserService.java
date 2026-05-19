package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    // REGISTER USER
    public User registerUser(User user) {

        return userRepository.save(user);
    }


    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if(user != null &&
                user.getPassword().equals(password)) {

            return user;
        }

        return null;
    }

}