package com.bhumi.backend.daoImpl;

import com.bhumi.backend.dao.VoteCumstomDAO;
import com.bhumi.backend.entity.Vote;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteDAOImpl implements VoteCumstomDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Vote> findAllByPost(Long postId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vote v WHERE v.post_id = :id", Vote.class);
        query.setParameter("id", postId);
        return query.getResultList();
    }

    @Override
    public List<Vote> findAllByUser(Long userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vote v WHERE v.user_id = :id", Vote.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public long countByPost(Long postId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) FROM vote v WHERE v.post_id = :id");
        query.setParameter("id", postId);
        return ((Number) query.getSingleResult()).longValue();
    }

    @Override
    public long countByComment(Long commentId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) FROM vote v WHERE v.comment_id = :id");
        query.setParameter("id", commentId);
        return ((Number) query.getSingleResult()).longValue();
    }

    @Override
    public boolean duplicateCommentVote(Long commentId, Long userId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) FROM vote v WHERE v.comment_id = :commentId AND v.user_id = :userId");
        query.setParameter("commentId", commentId);
        query.setParameter("userId", userId);
        return ((Number) query.getSingleResult()).intValue() == 0 ? false : true;
    }

    @Override
    public boolean duplicatePostVote(Long postId, Long userId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(v.id) FROM vote v WHERE v.post_id = :commentId AND v.user_id = :userId");
        query.setParameter("commentId", postId);
        query.setParameter("userId", userId);
        return ((Number) query.getSingleResult()).intValue() == 0 ? false : true;
    }
}
