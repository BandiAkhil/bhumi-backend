package com.bhumi.backend.controller;

import com.bhumi.backend.repository.Comment;
import com.bhumi.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("posts/{id}/comments")
    public ResponseEntity<List<List<Comment>>> getAllPostComments(@PathVariable("id") Long id) {
        List<List<Comment>> result = new ArrayList<>();
        List<Comment> parentComments = commentService.getAllParentComments();
        for (Comment comment: parentComments) {
            List<Comment> temp = recursiveCommentThreads(comment);
            System.out.println(temp);
            result.add(temp);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public List<Comment> recursiveCommentThreads(Comment comment) {
        List<Comment> result = new ArrayList<>();
        List<Comment> children = commentService.getChildComments(comment.getId());
        //System.out.println(comment);
        //System.out.println(children);
        if (children.size() != 0) {
            List<Comment> childList = new ArrayList<>();
            for (Comment child: children) {
                childList.addAll(recursiveCommentThreads(child));
            }
            result.add(0, comment);
            result.addAll(childList);
            System.out.println("{ comment: " + comment.getId() + " children: { " + childList + " }");
            return result;
        } else {
            return Arrays.asList(comment);
        }
    }

    @PostMapping("posts/{id}/comments")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    @PutMapping("posts/{postId}/comments/{commentId}/update")
    public ResponseEntity<Comment> updateCommentById(@PathVariable("commentId") Long id, @RequestBody Comment comment) {
        comment.setId(id);
        Comment commentUpdate = commentService.updateComment(comment);
        return new ResponseEntity<>(commentUpdate, HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}/delete")
    public ResponseEntity<?> deleteCommentById(@PathVariable("commentId") Long commentId) {
        commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
