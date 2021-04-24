package com.bhumi.backend.dao;

import com.bhumi.backend.repository.ForumAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumAnswerDAO extends JpaRepository<ForumAnswer, Long>, ForumAnswerCustomDAO {
}
