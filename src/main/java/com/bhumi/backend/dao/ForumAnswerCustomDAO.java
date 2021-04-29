package com.bhumi.backend.dao;

import java.util.List;

import com.bhumi.backend.entity.ForumAnswer;

public interface ForumAnswerCustomDAO {
    List<ForumAnswer> findAllByUser(Long userId);
    List<ForumAnswer> findAllByForum(Long forumId);
}
