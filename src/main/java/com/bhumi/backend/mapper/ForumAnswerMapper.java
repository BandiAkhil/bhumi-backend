package com.bhumi.backend.mapper;

import com.bhumi.backend.dto.ForumAnswerDTO;
import com.bhumi.backend.entity.ForumAnswer;
import com.bhumi.backend.service.AdminService;
import com.bhumi.backend.service.ForumService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForumAnswerMapper {
    
    private static final ModelMapper modelMapper = new ModelMapper();
    private final AdminService adminService;
    private final ForumService forumService;

    @Autowired
    public ForumAnswerMapper(AdminService adminService, ForumService forumService) {
        this.adminService = adminService;
        this.forumService = forumService;
    }

    public ForumAnswer DtoToEntity(ForumAnswerDTO forumAnswer) {
        ForumAnswer forumAnswerEntity = modelMapper.map(forumAnswer, ForumAnswer.class);
        forumAnswerEntity.setUser(adminService.getUserById(forumAnswer.getUserId()));
        forumAnswerEntity.setForum(forumService.getForumById(forumAnswer.getForumId()));
        return forumAnswerEntity;
    }
    
    public ForumAnswerDTO EntityToDto(ForumAnswer forumAnswer) {
        ForumAnswerDTO forumAnswerDTO = modelMapper.map(forumAnswer, ForumAnswerDTO.class);
        forumAnswerDTO.setUserId(forumAnswer.getUser().getId());
        forumAnswerDTO.setUsername(forumAnswer.getUser().getUsername());
        return forumAnswerDTO;
    }
}
