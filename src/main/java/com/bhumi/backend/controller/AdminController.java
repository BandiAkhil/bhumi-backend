package com.bhumi.backend.controller;

import com.bhumi.backend.dto.ImageDTO;
import com.bhumi.backend.entity.Post;
import com.bhumi.backend.entity.User;
import com.bhumi.backend.entity.Vote;
import com.bhumi.backend.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post newPost = adminService.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        post.setId(id);
        Post updatePost = adminService.updatePost(post);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable("id") Long id) {
        adminService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable("commentId") Long commentId) {
        adminService.deleteCommentById(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/forum/{id}")
    public ResponseEntity<?> deleteForumById(@PathVariable("id") Long id) {
        adminService.deleteForumById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("forums/{forumId}/forumAnswers/{forumAnswerId}")
    public ResponseEntity<?> deleteForumAnswerById(@PathVariable("forumAnswerId") Long id) {
        adminService.deleteForumAnswerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("posts/{id}/votes")
    public ResponseEntity<List<Vote>> getAllPostVotes(@PathVariable("id") Long id) {
        List<Vote> votes = adminService.getAllPostVotes(id);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = adminService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = adminService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updateUser = adminService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        adminService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PostMapping("users/{id}/images")
    // public ResponseEntity<ImageDTO> addImage(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") Long id) throws IOException {
    //     ImageDTO newImage = adminService.addUserImage(file, (Long) null, id);
    //     return new ResponseEntity<>(newImage, HttpStatus.OK);
    // }

    // @DeleteMapping("images/{imageId}")
    // public ResponseEntity<?> deleteImage(@PathVariable("imageId") Long imageId) {
    //     adminService.deleteImage(imageId);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }
}
