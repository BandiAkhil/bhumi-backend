package com.bhumi.backend.dao;

import java.util.List;

import com.bhumi.backend.entity.Comment;

public interface CommentCustomDAO {
    List<Comment> findAllByUser(Long userId);
    List<Comment> findAllPostComments(Long postId);
}
