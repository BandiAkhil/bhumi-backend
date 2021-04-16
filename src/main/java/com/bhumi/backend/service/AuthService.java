package com.bhumi.backend.service;

import com.bhumi.backend.dao.UserDAO;
import com.bhumi.backend.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserDAO userDAO) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @Transactional
    public User signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(LocalDate.now());
        user.setRole("User");
        return userDAO.save(user);
    }

    public void login(User user) {

    }
}
