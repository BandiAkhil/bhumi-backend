package com.bhumi.backend.mapper;

import com.bhumi.backend.dto.ImageDTO;
import com.bhumi.backend.entity.Image;
import com.bhumi.backend.service.AdminService;
import com.bhumi.backend.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    private final PostService postService;
    private final AdminService adminService;

    @Autowired
    public ImageMapper(PostService postService, AdminService adminService) {
        this.postService = postService;
        this.adminService = adminService;
    }

    public Image DtoToEntity(ImageDTO image) {
        Image imageEntity = new Image();
        imageEntity.setPost((image.getPostId() == null) ? null : postService.getPostById(image.getPostId()));
        imageEntity.setUser(adminService.getUserById(image.getUserId()));
        return imageEntity;
    }

    public ImageDTO EntityToDto(Image image) {
        ImageDTO imageDTO = modelMapper.map(image, ImageDTO.class);
        imageDTO.setPostId((image.getPost() == null) ? null : image.getPost().getId());
        imageDTO.setUserId(image.getUser().getId());
        return imageDTO;
    }
}
