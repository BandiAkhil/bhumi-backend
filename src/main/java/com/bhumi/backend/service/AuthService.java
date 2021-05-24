package com.bhumi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bhumi.backend.entity.LoginRequest;
import com.bhumi.backend.entity.LoginResponse;
import com.bhumi.backend.config.JwtTokenUtil;
import com.bhumi.backend.service.AuthService;
import com.bhumi.backend.entity.User;

import java.time.LocalDate;
import java.util.Objects;


@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

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

    public LoginResponse login(LoginRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        System.out.println("AAAHA :::::: " + request);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
        return new LoginResponse(userDetails.getUsername(), token);
    }

    private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return adminService.getUserByUsername(principal.getUsername());
    }
}
