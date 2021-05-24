package com.bhumi.backend.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bhumi.backend.dao.ImageCumstomDAO;
import com.bhumi.backend.entity.Image;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ImageDAOImpl implements ImageCumstomDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Image> findAllByPost(Long postId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM image i WHERE i.post_id = :id", Image.class);
        query.setParameter("id", postId);
        return query.getResultList();
    }

    @Override
    public List<Image> findByUser(Long userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM image i WHERE i.user_id = :id", Image.class);
        query.setParameter("id", userId);
        return query.setMaxResults(1).getResultList();
    }
    
}
