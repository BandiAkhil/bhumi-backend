package com.bhumi.backend.controller;

import com.bhumi.backend.repository.Post;
import com.bhumi.backend.repository.User;
import com.bhumi.backend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/posts/add")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post newPost = adminService.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}/update")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        post.setId(id);
        Post updatePost = adminService.updatePost(post);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}/delete")
    public ResponseEntity<?> deletePostById(@PathVariable("id") Long id) {
        adminService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}/delete")
    public ResponseEntity<?> deleteCommentById(@PathVariable("commentId") Long commentId) {
        adminService.deleteCommentById(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/forum/{id}/delete")
    public ResponseEntity<?> deleteForumById(@PathVariable("id") Long id) {
        adminService.deleteForumById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("forums/{forumId}/forumAnswers/{forumAnswerId}/delete")
    public ResponseEntity<?> deleteForumAnswerById(@PathVariable("forumAnswerId") Long id) {
        adminService.deleteForumAnswerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = adminService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("users/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = adminService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("users/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updateUser = adminService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}/delete")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        adminService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
