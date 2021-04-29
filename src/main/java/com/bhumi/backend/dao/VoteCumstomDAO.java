package com.bhumi.backend.dao;

import java.util.List;

import com.bhumi.backend.entity.Vote;

public interface VoteCumstomDAO {
    List<Vote> findAllByPost(Long postId);
    List<Vote> findAllByUser(Long userId);
    long countByPost(Long postId);
    long countByComment(Long commentId);
    boolean duplicateCommentVote(Long commentId, Long userId);
    boolean duplicatePostVote(Long postId, Long userId);
}
