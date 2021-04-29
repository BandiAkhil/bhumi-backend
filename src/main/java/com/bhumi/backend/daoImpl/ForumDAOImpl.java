package com.bhumi.backend.daoImpl;

import com.bhumi.backend.dao.ForumCustomDAO;
import com.bhumi.backend.entity.Forum;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ForumDAOImpl implements ForumCustomDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Forum> findAllByUser(Long userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM forum f WHERE f.user_id = :id", Forum.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }
}
