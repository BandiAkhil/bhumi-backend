package com.bhumi.backend.controller;

import com.bhumi.backend.dto.CommentDTO;
import com.bhumi.backend.dto.VoteDTO;
import com.bhumi.backend.service.CommentService;
import com.bhumi.backend.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;
    private final VoteService voteService;

    @Autowired
    public CommentController(CommentService commentService, VoteService voteService) {
        this.commentService = commentService;
        this.voteService = voteService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getAllPostComments(@PathVariable("id") Long id) {
        List<CommentDTO> comments = commentService.getAllPostComments(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("commentId") Long id) {
        CommentDTO comment = commentService.getCommentDTOById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable("postId") Long id, @RequestBody CommentDTO comment) {
        comment.setPostId(id);
        CommentDTO newComment = commentService.addComment(comment);
        voteService.addVote(new VoteDTO(null, newComment.getId(), null, null, newComment.getUserId()));
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateCommentById(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody CommentDTO comment) {
        comment.setPostId(postId);
        comment.setId(commentId);
        CommentDTO commentUpdate = commentService.updateComment(comment);
        return new ResponseEntity<>(commentUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable("commentId") Long commentId) {
        commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
