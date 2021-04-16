package com.bhumi.backend.dao;

import com.bhumi.backend.modal.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long>, CommentCustomDAO {
}
