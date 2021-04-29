package com.bhumi.backend.controller;

import com.bhumi.backend.dto.VoteDTO;
import com.bhumi.backend.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("posts/{id}/votes/count")
    public ResponseEntity<Integer> getPostVoteCount(@PathVariable("id") Long id) {
        int voteCount = voteService.getPostVoteCount(id);
        return new ResponseEntity<>(voteCount, HttpStatus.OK);
    }

    @PostMapping("posts/{id}/votes")
    public ResponseEntity<VoteDTO> addPostVote(@RequestBody VoteDTO vote, @PathVariable("id") Long id) {
        vote.setPostId(id);
        VoteDTO newVote = voteService.addVote(vote);
        return new ResponseEntity<>(newVote, HttpStatus.OK);
    }

    @GetMapping("posts/{postId}/comments/{commentId}/votes/count")
    public ResponseEntity<Integer> getCommentVoteCount(@PathVariable("commentId") Long id) {
        int voteCount = voteService.getCommentVoteCount(id);
        return new ResponseEntity<>(voteCount, HttpStatus.OK);
    }

    @PostMapping("posts/{postId}/comments/{commentId}/votes")
    public ResponseEntity<VoteDTO> addCommentVote(@RequestBody VoteDTO vote, @PathVariable("commentId") Long id) {
        vote.setCommentId(id);
        VoteDTO newVote = voteService.addVote(vote);
        return new ResponseEntity<>(newVote, HttpStatus.OK);
    }

    @GetMapping("forums/{forumId}/votes/count")
    public ResponseEntity<Integer> getForumVoteCount(@PathVariable("forumId") Long id) {
        int voteCount = voteService.getCommentVoteCount(id);
        return new ResponseEntity<>(voteCount, HttpStatus.OK);
    }

    @PostMapping("forums/{forumId}/votes")
    public ResponseEntity<VoteDTO> addForumVote(@RequestBody VoteDTO vote) {
        VoteDTO newVote = voteService.addVote(vote);
        return new ResponseEntity<>(newVote, HttpStatus.OK);
    }

    @GetMapping("forums/{forumId}/forum-answers/{forumAnswerId}/votes/count")
    public ResponseEntity<Integer> getForumAnswerVoteCount(@PathVariable("forumAnswerId") Long id) {
        int voteCount = voteService.getCommentVoteCount(id);
        return new ResponseEntity<>(voteCount, HttpStatus.OK);
    }

    @PostMapping("forums/{forumId}/forum-answers/{forumAnswerId}/votes")
    public ResponseEntity<VoteDTO> addForumAnswerVote(@RequestBody VoteDTO vote) {
        VoteDTO newVote = voteService.addVote(vote);
        return new ResponseEntity<>(newVote, HttpStatus.OK);
    }

    @DeleteMapping("votes/{voteId}")
    public ResponseEntity<?> deleteVote(@PathVariable("voteId") Long voteId) {
        voteService.deleteVote(voteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
