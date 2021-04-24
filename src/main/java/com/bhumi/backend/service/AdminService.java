package com.bhumi.backend.service;

import com.bhumi.backend.dao.*;
import com.bhumi.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    private final PostDAO postDAO;
    private final CommentDAO commentDAO;
    private final ForumDAO forumDAO;
    private final ForumAnswerDAO forumAnswerDAO;
    private final UserDAO userDAO;

    @Autowired
    public AdminService(PostDAO postDAO, CommentDAO commentDAO, ForumDAO forumDAO, ForumAnswerDAO forumAnswerDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.commentDAO = commentDAO;
        this.forumDAO = forumDAO;
        this.forumAnswerDAO = forumAnswerDAO;
        this.userDAO = userDAO;
    }

    @Transactional
    public Post addPost(Post post) {
        post.setDate(LocalDate.now());
        return postDAO.save(post);
    }

    @Transactional
    public Post updatePost(Post post) {
        post.setDate(LocalDate.now());
        return postDAO.save(post);
    }

    public void deletePostById(Long id) {
        postDAO.deleteById(id);
    }

    public void deleteCommentById(Long id) {
        if(!commentDAO.existsById(id)) {
            throw new RuntimeException("Comment with id " + id + " was not found");
        }
        commentDAO.deleteById(id);
    }

    public void deleteForumById(Long id) {
        forumDAO.deleteById(id);
    }

    public void deleteForumAnswerById(Long id) {
        forumAnswerDAO.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("User by id " + id + " was not found"));
    }

    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException("User by username " + username + " was not found"));
    }

    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new RuntimeException("User by email " + email + " was not found"));
    }

    public User getUserByUsernameOrEmail(String username, String email) {
        return userDAO.findByUsernameOrEmail(username, email).orElseThrow(() -> new RuntimeException("User by email or username " + username + " was not found"));
    }

    public User addUser(User user) {
        //user.setRole("USER");
        user.setCreated(LocalDate.now());
        return userDAO.save(user);
    }

    public User updateUser(User user) {
        //user.setRole("USER");
        User original = this.getUserById(user.getId());
        user.setCreated(original.getCreated());
        return userDAO.save(user);
    }

    public void deleteUserById(Long id) {
        userDAO.deleteById(id);
    }
}
