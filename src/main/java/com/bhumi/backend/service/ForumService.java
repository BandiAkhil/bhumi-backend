package com.bhumi.backend.service;

import com.bhumi.backend.dao.ForumDAO;
import com.bhumi.backend.dto.ForumDTO;
import com.bhumi.backend.entity.Forum;
import com.bhumi.backend.mapper.ForumMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForumService {

    private final ForumDAO forumDAO;
    private final ForumMapper forumMapper;

    @Autowired
    public ForumService(ForumDAO forumDAO, ForumMapper forumMapper) {
        this.forumDAO = forumDAO;
        this.forumMapper = forumMapper;
    }

    public List<ForumDTO> getAllForums() {
        List<ForumDTO> resultForums = new ArrayList<>();
        List<Forum> forums = forumDAO.findAll();
        for (Forum forum : forums) {
            resultForums.add(forumMapper.EntityToDto(forum));
        }
        return resultForums;
    }

    public Forum getForumById(Long id) {
        return forumDAO.findById(id).orElseThrow(() -> new RuntimeException("Forum by id " + id + " was not found"));
    }

    public ForumDTO getForumDTOById(Long id) {
        Forum forum = forumDAO.findById(id).orElseThrow(() -> new RuntimeException("Forum by id " + id + " was not found"));
        return forumMapper.EntityToDto(forum);
    }

    @Transactional
    public ForumDTO addForum(ForumDTO forum) {
        forum.setDate(LocalDate.now());
        Forum forumEntity = forumMapper.DtoToEntity(forum);
        Forum newForum = forumDAO.save(forumEntity);
        return forumMapper.EntityToDto(newForum);
    }

    @Transactional
    public ForumDTO updateForum(ForumDTO forum) {
        forum.setDate(LocalDate.now());
        Forum forumEntity = forumMapper.DtoToEntity(forum);
        Forum newForum = forumDAO.save(forumEntity);
        return forumMapper.EntityToDto(newForum);
    }

    public void deleteForumById(Long id) {
        forumDAO.deleteById(id);
    }
}
