package com.bhumi.backend.daoImpl;

import com.bhumi.backend.dao.CommentCustomDAO;
import com.bhumi.backend.entity.Comment;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CommentDAOImpl implements CommentCustomDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Comment> findAllByUser(Long userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM comment c WHERE c.user_id = :id", Comment.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAllPostComments(Long postId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM comment c WHERE c.post_id = :id ORDER BY (SELECT COUNT(v.id) FROM vote v WHERE v.post_id = :id) DESC", Comment.class);
        query.setParameter("id", postId);
        return query.getResultList();
    }
}
