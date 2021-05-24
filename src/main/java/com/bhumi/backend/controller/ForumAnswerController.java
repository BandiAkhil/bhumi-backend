package com.bhumi.backend.controller;

import com.bhumi.backend.dto.ForumAnswerDTO;
import com.bhumi.backend.dto.VoteDTO;
import com.bhumi.backend.service.ForumAnswerService;
import com.bhumi.backend.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forums/")
public class ForumAnswerController {

    private final ForumAnswerService forumAnswerService;
    private final VoteService voteService;

    @Autowired
    public ForumAnswerController(ForumAnswerService forumAnswerService, VoteService voteService) {
        this.forumAnswerService = forumAnswerService;
        this.voteService = voteService;
    }

    @GetMapping("{id}/forum-answers")
    public ResponseEntity<List<ForumAnswerDTO>> getAllForumAnswers(@PathVariable("id") Long id) {
        List<ForumAnswerDTO> forumAnswers = forumAnswerService.getAllForumAnswers(id);
        return new ResponseEntity<>(forumAnswers, HttpStatus.OK);
    }

    @PostMapping("{id}/forum-answers")
    public ResponseEntity<ForumAnswerDTO> addForumAnswer(@RequestBody ForumAnswerDTO forumAnswer) {
        ForumAnswerDTO newForumAnswer = forumAnswerService.addForumAnswer(forumAnswer);
        voteService.addVote(new VoteDTO(null, null, null, newForumAnswer.getId(), newForumAnswer.getUserId()));
        return new ResponseEntity<>(newForumAnswer, HttpStatus.OK);
    }

    @PutMapping("{forumId}/forum-answers/{forumAnswerId}")
    public ResponseEntity<ForumAnswerDTO> updateForumAnswerById(@PathVariable("forumAnswerId") Long id, @RequestBody ForumAnswerDTO forumAnswer) {
        forumAnswer.setId(id);
        ForumAnswerDTO forumAnswerUpdate = forumAnswerService.updateForumAnswer(forumAnswer);
        return new ResponseEntity<>(forumAnswerUpdate, HttpStatus.OK);
    }

    @DeleteMapping("{forumId}/forum-answers/{forumAnswerId}")
    public ResponseEntity<?> deleteForumAnswerById(@PathVariable("forumAnswerId") Long id) {
        forumAnswerService.deleteForumAnswerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
