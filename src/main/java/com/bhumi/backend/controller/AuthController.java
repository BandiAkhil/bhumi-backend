package com.bhumi.backend.controller;

import com.bhumi.backend.entity.LoginRequest;
import com.bhumi.backend.entity.LoginResponse;
import com.bhumi.backend.entity.User;
import com.bhumi.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User newUser = authService.signup(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
        System.out.println("REquest::::::::::::::::::::::::::::::::");
        LoginResponse jwtResponse = authService.login(request);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
