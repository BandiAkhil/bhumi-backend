package com.bhumi.backend.dao;

import com.bhumi.backend.repository.Comment;

import java.util.List;

public interface CommentCustomDAO {
    List<Comment> findAllByUser(Long userId);
    List<Comment> findAllParentComments();
    List<Comment> findAllChildComments(Long parentId);
}
