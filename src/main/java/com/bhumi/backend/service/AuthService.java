package com.bhumi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import com.bhumi.backend.entity.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Enumeration;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, AdminService adminService) {
        this.passwordEncoder = passwordEncoder;
        this.adminService = adminService;
    }

    @Transactional
    public User signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(LocalDate.now());
        user.setRole("USER");
        return adminService.addUser(user);
    }

    public boolean login(HttpServletRequest request) {
        Enumeration<String> names = request.getAttributeNames();
        for (String name : Collections.list(names)) {
            System.out.println("Attribute Name: " + name);
            System.out.println("Attribute Value: " + request.getAttribute(name));
        }
        String username = request.getAttribute("username").toString();
        User user = adminService.getUserByUsernameOrEmail(username, username);
        return user != null && passwordEncoder.matches(request.getAttribute("password").toString(), user.getPassword());
    }
}
