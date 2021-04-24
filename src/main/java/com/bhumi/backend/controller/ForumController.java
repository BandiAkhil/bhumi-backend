package com.bhumi.backend.controller;

import com.bhumi.backend.repository.Forum;
import com.bhumi.backend.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forums")
public class ForumController {

    private final ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Forum>> getAllForums() {
        List<Forum> forums = forumService.getAllForums();
        return new ResponseEntity<>(forums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable("id") Long id) {
        Forum forum = forumService.getForumById(id);
        return new ResponseEntity<>(forum, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Forum> addForum(@RequestBody Forum forum) {
        Forum newForum = forumService.addForum(forum);
        return new ResponseEntity<>(newForum, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Forum> updateForum(@PathVariable("id") Long id, @RequestBody Forum forum) {
        forum.setId(id);
        Forum updateForum = forumService.updateForum(forum);
        return new ResponseEntity<>(updateForum, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteForumById(@PathVariable("id") Long id) {
        forumService.deleteForumById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
