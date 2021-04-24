package com.bhumi.backend.dao;

import com.bhumi.backend.repository.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumDAO extends JpaRepository<Forum, Long>, ForumCustomDAO {
}
