package com.bhumi.backend.dao;

import java.util.List;

import com.bhumi.backend.entity.Forum;

public interface ForumCustomDAO {
    List<Forum> findAllByUser(Long userId);
}
