package com.bhumi.backend.controller;

import com.bhumi.backend.repository.Vote;
import com.bhumi.backend.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("posts/{id}/votes")
    public ResponseEntity<List<Vote>> getAllPostVotes(@PathVariable("id") Long id) {
        List<Vote> votes = voteService.getAllPostVotes(id);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }

    @PostMapping("posts/{id}/votes")
    public ResponseEntity<Vote> addVote(@RequestBody Vote vote) {
        Vote newVote = voteService.addVote(vote);
        return new ResponseEntity<>(newVote, HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/votes/{voteId}/delete")
    public ResponseEntity<?> deleteVote(@PathVariable("voteId") Long voteId) {
        voteService.deleteVote(voteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}