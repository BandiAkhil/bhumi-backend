package com.bhumi.backend.controller;

import com.bhumi.backend.repository.ForumAnswer;
import com.bhumi.backend.service.ForumAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forums/")
public class ForumAnswerController {

    private final ForumAnswerService forumAnswerService;

    @Autowired
    public ForumAnswerController(ForumAnswerService forumAnswerService) {
        this.forumAnswerService = forumAnswerService;
    }

    @GetMapping("{id}/forum-answers")
    public ResponseEntity<List<ForumAnswer>> getAllForumAnswers(@PathVariable("id") Long id) {
        List<ForumAnswer> forumAnswers = forumAnswerService.getAllForumAnswers(id);
        return new ResponseEntity<>(forumAnswers, HttpStatus.OK);
    }

    @PostMapping("{id}/forum-answers")
    public ResponseEntity<ForumAnswer> addForumAnswer(@RequestBody ForumAnswer forumAnswer) {
        ForumAnswer newForumAnswer = forumAnswerService.addForumAnswer(forumAnswer);
        return new ResponseEntity<>(newForumAnswer, HttpStatus.OK);
    }

    @PutMapping("{forumId}/forum-answers/{forumAnswerId}/update")
    public ResponseEntity<ForumAnswer> updateForumAnswerById(@PathVariable("forumAnswerId") Long id, @RequestBody ForumAnswer forumAnswer) {
        forumAnswer.setId(id);
        ForumAnswer forumAnswerUpdate = forumAnswerService.updateForumAnswer(forumAnswer);
        return new ResponseEntity<>(forumAnswerUpdate, HttpStatus.OK);
    }

    @DeleteMapping("{forumId}/forum-answers/{forumAnswerId}/delete")
    public ResponseEntity<?> deleteForumAnswerById(@PathVariable("forumAnswerId") Long id) {
        forumAnswerService.deleteForumAnswerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
