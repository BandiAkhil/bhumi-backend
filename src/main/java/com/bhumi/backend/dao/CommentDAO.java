package com.bhumi.backend.dao;

import com.bhumi.backend.repository.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long>, CommentCustomDAO {
}
