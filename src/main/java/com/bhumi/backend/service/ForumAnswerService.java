package com.bhumi.backend.service;

import com.bhumi.backend.dao.ForumAnswerDAO;
import com.bhumi.backend.dao.ForumDAO;
import com.bhumi.backend.dto.ForumAnswerDTO;
import com.bhumi.backend.entity.ForumAnswer;
import com.bhumi.backend.mapper.ForumAnswerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForumAnswerService {

    private final ForumAnswerDAO forumAnswerDAO;
    private final ForumDAO forumDAO;
    private final ForumAnswerMapper forumAnswerMapper;

    @Autowired
    public ForumAnswerService(ForumAnswerDAO forumAnswerDAO, ForumDAO forumDAO, ForumAnswerMapper forumAnswerMapper) {
        this.forumAnswerDAO = forumAnswerDAO;
        this.forumDAO = forumDAO;
        this.forumAnswerMapper = forumAnswerMapper;
    }

    public List<ForumAnswerDTO> getAllForumAnswers(Long forumId) {
        List<ForumAnswerDTO> resultForumAnswers = new ArrayList<>();
        List<ForumAnswer> forumAnswers = forumAnswerDAO.findAllByForum(forumId);
        if(!forumDAO.existsById(forumId)) {
            throw new RuntimeException("Forum with id " + forumId + " was not found");
        }
        for (ForumAnswer forumAnswer : forumAnswers) {
            resultForumAnswers.add(forumAnswerMapper.EntityToDto(forumAnswer));
        }
        return resultForumAnswers;
    }

    public ForumAnswer getForumAnswerById(Long id) {
        return forumAnswerDAO.findById(id).orElseThrow(() -> new RuntimeException("Forum answer by id " + id + " was not found"));
    }

    public ForumAnswerDTO getForumAnswerDTOById(Long id) {
        ForumAnswer forumAnswer = forumAnswerDAO.findById(id).orElseThrow(() -> new RuntimeException("Forum answer by id " + id + " was not found"));
        return forumAnswerMapper.EntityToDto(forumAnswer);
    }

    @Transactional
    public ForumAnswerDTO addForumAnswer(ForumAnswerDTO forumAnswer) {
        forumAnswer.setDate(LocalDate.now());
        ForumAnswer forumAnswerEntity = forumAnswerMapper.DtoToEntity(forumAnswer);
        ForumAnswer newForumAnswer = forumAnswerDAO.save(forumAnswerEntity);
        return forumAnswerMapper.EntityToDto(newForumAnswer);
    }

    @Transactional
    public ForumAnswerDTO updateForumAnswer(ForumAnswerDTO forumAnswer) {
        forumAnswer.setDate(LocalDate.now());
        ForumAnswer forumAnswerEntity = forumAnswerMapper.DtoToEntity(forumAnswer);
        ForumAnswer newForumAnswer = forumAnswerDAO.save(forumAnswerEntity);
        return forumAnswerMapper.EntityToDto(newForumAnswer);
    }

    public void deleteForumAnswerById(Long id) {
        forumAnswerDAO.deleteById(id);
    }
}
