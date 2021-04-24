package com.bhumi.backend.daoImpl;

import com.bhumi.backend.dao.ForumAnswerCustomDAO;
import com.bhumi.backend.repository.ForumAnswer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ForumAnswerDAOImpl implements ForumAnswerCustomDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ForumAnswer> findAllByUser(Long userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM comment c WHERE c.user_id = :id", ForumAnswer.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public List<ForumAnswer> findAllByForum(Long forumId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM comment c WHERE c.forum_id = :id", ForumAnswer.class);
        query.setParameter("id", forumId);
        return query.getResultList();
    }
}
