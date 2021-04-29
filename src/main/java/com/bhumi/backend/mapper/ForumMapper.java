package com.bhumi.backend.mapper;

import com.bhumi.backend.dto.ForumDTO;
import com.bhumi.backend.entity.Forum;
import com.bhumi.backend.service.AdminService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForumMapper {
    
    private static final ModelMapper modelMapper = new ModelMapper();
    private final AdminService adminService;

    @Autowired
    public ForumMapper(AdminService adminService) {
        this.adminService = adminService;
    }

    public Forum DtoToEntity(ForumDTO forum) {
        Forum forumEntity = modelMapper.map(forum, Forum.class);
        forumEntity.setUser(adminService.getUserById(forum.getUserId()));
        return forumEntity;
    }
    
    public ForumDTO EntityToDto(Forum forum) {
        ForumDTO forumDTO = modelMapper.map(forum, ForumDTO.class);
        forumDTO.setUserId(forum.getUser().getId());
        forumDTO.setUsername(forum.getUser().getUsername());
        return forumDTO;
    }
}
