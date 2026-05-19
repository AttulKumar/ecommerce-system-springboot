package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // REGISTER API
    @PostMapping("/register")
    public User registerUser(
            @RequestBody User user) {

        return userService.registerUser(user);
    }


@PostMapping("/login")
public User loginUser(@RequestBody User user) {

    return userService.loginUser(
            user.getEmail(),
            user.getPassword()
    );
}
}