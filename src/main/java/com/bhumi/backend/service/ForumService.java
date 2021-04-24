package com.bhumi.backend.service;

import com.bhumi.backend.dao.ForumDAO;
import com.bhumi.backend.repository.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForumService {

    private final ForumDAO forumDAO;

    @Autowired
    public ForumService(ForumDAO forumDAO) {
        this.forumDAO = forumDAO;
    }

    public List<Forum> getAllForums() {
        return forumDAO.findAll();
    }

    public Forum getForumById(Long id) {
        return forumDAO.findById(id).orElseThrow(() -> new RuntimeException("Forum by id " + id + " was not found"));
    }

    @Transactional
    public Forum addForum(Forum forum) {
        forum.setDate(LocalDate.now());
        return forumDAO.save(forum);
    }

    @Transactional
    public Forum updateForum(Forum forum) {
        forum.setDate(LocalDate.now());
        return forumDAO.save(forum);
    }

    public void deleteForumById(Long id) {
        forumDAO.deleteById(id);
    }
}
