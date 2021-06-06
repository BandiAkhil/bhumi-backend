package com.bhumi.backend.controller;

import java.util.List;

import com.bhumi.backend.entity.Video;
import com.bhumi.backend.service.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/isha")
    public ResponseEntity<List<Video>> getIshaVideos() {
        List<Video> videos = videoService.getIshaVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<List<Video>> getHomeVideos() {
        List<Video> videos = videoService.getHomeVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
}
