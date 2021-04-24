package com.bhumi.backend.service;

import com.bhumi.backend.dao.ForumAnswerDAO;
import com.bhumi.backend.repository.ForumAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForumAnswerService {

    private final ForumAnswerDAO forumAnswerDAO;

    @Autowired
    public ForumAnswerService(ForumAnswerDAO forumAnswerDAO) {
        this.forumAnswerDAO = forumAnswerDAO;
    }

    public List<ForumAnswer> getAllForumAnswers(Long forumId) {
        if(!forumAnswerDAO.existsById(forumId)) {
            throw new RuntimeException("Forum with id " + forumId + " was not found");
        }
        return forumAnswerDAO.findAllByUser(forumId);
    }

    public ForumAnswer getForumAnswerById(Long id) {
        return forumAnswerDAO.findById(id).orElseThrow(() -> new RuntimeException("Forum answer by id " + id + " was not found"));
    }

    @Transactional
    public ForumAnswer addForumAnswer(ForumAnswer forumAnswer) {
        forumAnswer.setDate(LocalDate.now());
        return forumAnswerDAO.save(forumAnswer);
    }

    @Transactional
    public ForumAnswer updateForumAnswer(ForumAnswer forumAnswer) {
        forumAnswer.setDate(LocalDate.now());
        return forumAnswerDAO.save(forumAnswer);
    }

    public void deleteForumAnswerById(Long id) {
        forumAnswerDAO.deleteById(id);
    }
}
