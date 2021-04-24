package com.bhumi.backend.dao;

import com.bhumi.backend.repository.ForumAnswer;

import java.util.List;

public interface ForumAnswerCustomDAO {
    List<ForumAnswer> findAllByUser(Long userId);
    List<ForumAnswer> findAllByForum(Long forumId);
}
