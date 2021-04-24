package com.bhumi.backend.service;

import com.bhumi.backend.dao.*;
import com.bhumi.backend.repository.Comment;
import com.bhumi.backend.repository.Forum;
import com.bhumi.backend.repository.ForumAnswer;
import com.bhumi.backend.repository.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final UserDAO userDAO;
    private final CommentDAO commentDAO;
    private final ForumDAO forumDAO;
    private final ForumAnswerDAO forumAnswerDAO;
    private final VoteDAO voteDAO;

    @Autowired
    public AccountService(UserDAO userDAO, CommentDAO commentDAO, ForumDAO forumDAO, ForumAnswerDAO forumAnswerDAO, VoteDAO voteDAO) {
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
        this.forumDAO = forumDAO;
        this.forumAnswerDAO = forumAnswerDAO;
        this.voteDAO = voteDAO;
    }

    public List<Comment> getAllUserComments(Long userId) {
        if(!userDAO.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " was not found");
        }
        return commentDAO.findAllByUser(userId);
    }

    public List<ForumAnswer> getAllUserForumAnswers(Long userId) {
        if(!forumAnswerDAO.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " was not found");
        }
        return forumAnswerDAO.findAllByUser(userId);
    }

    public List<Forum> getAllUserForums(Long userId) {
        if(!forumDAO.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " was not found");
        }
        return forumDAO.findAllByUser(userId);
    }

    public List<Vote> getAllUserVotes(Long userId) {
        if(!userDAO.existsById(userId)) {
            throw new RuntimeException("User with id " + userId + " was not found");
        }
        return voteDAO.findAllByUser(userId);
    }
}
