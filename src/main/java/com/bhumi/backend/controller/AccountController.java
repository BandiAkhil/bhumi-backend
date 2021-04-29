package com.bhumi.backend.controller;

import com.bhumi.backend.entity.*;
import com.bhumi.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getAllUserComments(@PathVariable("id") Long id) {
        List<Comment> comments = accountService.getAllUserComments(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}/forum-answers")
    public ResponseEntity<List<ForumAnswer>> getAllUserForumAnswers(@PathVariable("id") Long id) {
        List<ForumAnswer> forumAnswers = accountService.getAllUserForumAnswers(id);
        return new ResponseEntity<>(forumAnswers, HttpStatus.OK);
    }

    @GetMapping("/{id}/forums")
    public ResponseEntity<List<Forum>> getAllUserForums(@PathVariable("id") Long id) {
        List<Forum> forums = accountService.getAllUserForums(id);
        return new ResponseEntity<>(forums, HttpStatus.OK);
    }

    @GetMapping("/{id}/votes")
    public ResponseEntity<List<Vote>> getAllUserVotes(@PathVariable("id") Long id) {
        List<Vote> votes = accountService.getAllUserVotes(id);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }
}
