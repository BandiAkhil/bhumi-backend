package com.bhumi.backend.service;

import com.bhumi.backend.dao.PostDAO;
import com.bhumi.backend.dao.UserDAO;
import com.bhumi.backend.dao.VoteDAO;
import com.bhumi.backend.repository.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteDAO voteDAO;
    private final UserDAO userDAO;
    private final PostDAO postDAO;

    public VoteService(VoteDAO voteDAO, UserDAO userDAO, PostDAO postDAO) {
        this.voteDAO = voteDAO;
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    public Integer getPostVoteCount(Long postId) {
        if(!postDAO.existsById(postId)) {
            throw new RuntimeException("Post with id " + postId + " was not found");
        }
        return (int) voteDAO.countByPost(postId);
    }

    public List<Vote> getAllPostVotes(Long postId) {
        if(!postDAO.existsById(postId)) {
            throw new RuntimeException("Post with id " + postId + " was not found");
        }
        return voteDAO.findAllByPost(postId);
    }

    public Vote getVoteById(Long id) {
        return voteDAO.findById(id).orElseThrow(() -> new RuntimeException("Comment by id " + id + " was not found"));
    }

    public Vote addVote(Vote vote) {
        if(!postDAO.existsById(vote.getPost().getId())) {
            throw new RuntimeException("Post with id " + vote.getPost().getId() + " was not found");
        }
        return voteDAO.save(vote);
    }

    public void deleteVote(Long id) {
        if(!voteDAO.existsById(id)) {
            throw new RuntimeException("Vote with id " + id + " was not found");
        }
        voteDAO.deleteById(id);
    }
}
