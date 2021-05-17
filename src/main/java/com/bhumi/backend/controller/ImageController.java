package com.bhumi.backend.controller;

import java.io.IOException;
import java.util.List;

import com.bhumi.backend.dto.ImageDTO;
import com.bhumi.backend.service.AdminService;
import com.bhumi.backend.service.ImageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/")
public class ImageController {

    private final ImageService imageService;
    private final AdminService adminService;
    
    public ImageController(ImageService imageService, AdminService adminService) {
        this.imageService = imageService;
        this.adminService = adminService;
    }

    @GetMapping("posts/{id}/images")
    public ResponseEntity<List<ImageDTO>> getPostImages(@PathVariable("id") Long id) {
        List<ImageDTO> postImages = imageService.getPostImages(id);
        return new ResponseEntity<>(postImages, HttpStatus.OK);
    }

    @PostMapping("posts/{id}/images")
    public ResponseEntity<ImageDTO> addPostImage(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") Long id) throws IOException {
        ImageDTO newImage = imageService.addPostImage(file, id, (Long) null);
        return new ResponseEntity<>(newImage, HttpStatus.OK);
    }

}
