package com.bhumi.backend.daoImpl;

import com.bhumi.backend.dao.CommentCustomDAO;
import com.bhumi.backend.repository.Comment;
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
    public List<Comment> findAllParentComments() {
        Query query = entityManager.createNativeQuery("SELECT * FROM comment c WHERE c.parent_comment_id IS null ORDER BY c.votes DESC", Comment.class);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAllChildComments(Long parentId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM comment c WHERE c.parent_comment_id = :id ORDER BY c.votes DESC", Comment.class);
        query.setParameter("id", parentId);
        return query.getResultList();
    }
}
