package com.bhumi.backend.dao;

import com.bhumi.backend.repository.Vote;

import java.util.List;

public interface VoteCumstomDAO {
    List<Vote> findAllByPost(Long postId);
    List<Vote> findAllByUser(Long userId);
    long countByPost(Long postId);
}
