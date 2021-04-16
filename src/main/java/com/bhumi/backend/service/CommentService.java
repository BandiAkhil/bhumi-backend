package com.bhumi.backend.service;

import com.bhumi.backend.dao.CommentDAO;
import com.bhumi.backend.dao.PostDAO;
import com.bhumi.backend.dao.UserDAO;
import com.bhumi.backend.dto.CommentDTO;
import com.bhumi.backend.modal.Comment;
import com.bhumi.backend.modal.Post;
import com.bhumi.backend.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    private final CommentDAO commentDAO;
    private final UserDAO userDAO;
    private final PostDAO postDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO, UserDAO userDAO, PostDAO postDAO) {
        this.commentDAO = commentDAO;
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    public List<Comment> getAllParentComments() {
        return commentDAO.findAllParentComments();
    }

    public List<Comment> getChildComments(Long parentId) {
        return commentDAO.findAllChildComments(parentId);
    }

    public List<Comment> getAllUserComments(Long userId) {
        if(!userDAO.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " was not found");
        }
        return commentDAO.findAllByUser(userId);
    }

    public Comment getCommentById(Long id) {
        return commentDAO.findById(id).orElseThrow(() -> new RuntimeException("Comment by id " + id + " was not found"));
    }

    @Transactional
    public Comment addComment(Comment comment) {
        if(!postDAO.existsById(comment.getPost().getId())) {
            throw new RuntimeException("Post with id " + comment.getPost().getId() + " was not found");
        }
        comment.setUpdated(LocalDate.now());
        return commentDAO.save(comment);
    }

    @Transactional
    public Comment updateComment(Comment comment) {
        if(!postDAO.existsById(comment.getPost().getId())) {
            throw new RuntimeException("Post with id " + comment.getPost().getId() + " was not found");
        }
        comment.setUpdated(LocalDate.now());
        return commentDAO.save(comment);
    }

    public void deleteCommentById(Long id) {
        if(!commentDAO.existsById(id)) {
            throw new RuntimeException("Comment with id " + id + " was not found");
        }
        commentDAO.deleteById(id);
    }
}
