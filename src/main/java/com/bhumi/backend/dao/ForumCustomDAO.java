package com.bhumi.backend.dao;

import com.bhumi.backend.repository.Forum;

import java.util.List;

public interface ForumCustomDAO {
    List<Forum> findAllByUser(Long userId);
}
