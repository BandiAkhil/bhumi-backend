package com.bhumi.backend.service;

import com.bhumi.backend.dao.CommentDAO;
import com.bhumi.backend.dao.PostDAO;
import com.bhumi.backend.dao.VoteDAO;
import com.bhumi.backend.dto.VoteDTO;
import com.bhumi.backend.entity.Vote;
import com.bhumi.backend.mapper.VoteMapper;

import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteDAO voteDAO;
    private final PostDAO postDAO;
    private final CommentDAO commentDAO;
    private final VoteMapper voteMapper;

    public VoteService(VoteDAO voteDAO, PostDAO postDAO, CommentDAO commentDAO, VoteMapper voteMapper) {
        this.voteDAO = voteDAO;
        this.postDAO = postDAO;
        this.commentDAO = commentDAO;
        this.voteMapper = voteMapper;
    }

    public Integer getPostVoteCount(Long postId) {
        if(!postDAO.existsById(postId)) {
            throw new RuntimeException("Post with id " + postId + " was not found");
        }
        return (int) voteDAO.countByPost(postId);
    }

    public Integer getCommentVoteCount(Long commentId) {
        if(!commentDAO.existsById(commentId)) {
            throw new RuntimeException("Comment with id " + commentId + " was not found");
        }
        return (int) voteDAO.countByComment(commentId);
    }

    public VoteDTO getVoteById(Long id) {
        Vote resultVote = voteDAO.findById(id).orElseThrow(() -> new RuntimeException("Comment by id " + id + " was not found"));
        return voteMapper.EntityToDto(resultVote);
    }

    public VoteDTO addVote(VoteDTO vote) {
        if(vote.getPostId() != null && voteDAO.duplicatePostVote(vote.getPostId(), vote.getUserId())) {
            deleteVote(voteMapper.DtoToEntity(vote).getId());
            return null;
        } else if(vote.getCommentId() != null && voteDAO.duplicateCommentVote(vote.getCommentId(), vote.getUserId())) {
            deleteVote(voteMapper.DtoToEntity(vote).getId());
            return null;
        }
        Vote voteEntity = voteMapper.DtoToEntity(vote);
        Vote resultVote = voteDAO.save(voteEntity);
        return voteMapper.EntityToDto(resultVote);
    }

    public void deleteVote(Long id) {
        if(!voteDAO.existsById(id)) {
            throw new RuntimeException("Vote with id " + id + " was not found");
        }
        voteDAO.deleteById(id);
    }
}
