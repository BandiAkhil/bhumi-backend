package com.bhumi.backend.controller;

import com.bhumi.backend.dto.ForumDTO;
import com.bhumi.backend.dto.VoteDTO;
import com.bhumi.backend.service.ForumService;
import com.bhumi.backend.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forums")
public class ForumController {

    private final ForumService forumService;
    private final VoteService voteService;

    @Autowired
    public ForumController(ForumService forumService, VoteService voteService) {
        this.forumService = forumService;
        this.voteService = voteService;
    }

    @GetMapping("")
    public ResponseEntity<List<ForumDTO>> getAllForums() {
        List<ForumDTO> forums = forumService.getAllForums();
        return new ResponseEntity<>(forums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumDTO> getForumById(@PathVariable("id") Long id) {
        ForumDTO forum = forumService.getForumDTOById(id);
        return new ResponseEntity<>(forum, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ForumDTO> addForum(@RequestBody ForumDTO forum) {
        ForumDTO newForum = forumService.addForum(forum);
        voteService.addVote(new VoteDTO(null, null, newForum.getId(), null, newForum.getUserId()));
        return new ResponseEntity<>(newForum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDTO> updateForum(@PathVariable("id") Long id, @RequestBody ForumDTO forum) {
        forum.setId(id);
        ForumDTO updateForum = forumService.updateForum(forum);
        return new ResponseEntity<>(updateForum, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForumById(@PathVariable("id") Long id) {
        forumService.deleteForumById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
