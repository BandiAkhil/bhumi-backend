package com.bhumi.backend.mapper;

import com.bhumi.backend.dto.CommentDTO;
import com.bhumi.backend.entity.Comment;
import com.bhumi.backend.service.AdminService;
import com.bhumi.backend.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private static final ModelMapper modelMapper = new ModelMapper();
    private final PostService postService;
    private final AdminService adminService;

    @Autowired
    public CommentMapper(PostService postService, AdminService adminService) {
        this.postService = postService;
        this.adminService = adminService;
    }

    public Comment DtoToEntity(CommentDTO comment) {
        Comment commentEntity = modelMapper.map(comment, Comment.class);
        commentEntity.setPost(postService.getPostById(comment.getPostId()));
        commentEntity.setUser(adminService.getUserById(comment.getUserId()));
        return commentEntity;
    }

    public CommentDTO EntityToDto(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setUsername(comment.getUser().getUsername());
        return commentDTO;
    }
}
