package com.bhumi.backend.service;

import com.bhumi.backend.dao.UserDAO;
import com.bhumi.backend.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("User by id " + id + " was not found"));
    }

    public User addUser(User user) {
        return userDAO.save(user);
    }

    public User updateUser(User user) {
        return userDAO.save(user);
    }

    public void deleteUserById(Long id) {
        userDAO.deleteById(id);
    }
}
