package com.bhumi.backend.service;

import java.util.List;

import com.bhumi.backend.dao.VideoDAO;
import com.bhumi.backend.entity.Video;

import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final VideoDAO videoDAO;

    public VideoService(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    public List<Video> getIshaVideos() {
        return videoDAO.findByIshaVideoTrueOrderByUploadedAsc();
    }

    public List<Video> getHomeVideos() {
        return videoDAO.findByHomeVideoTrueOrderByUploadedAsc();
    }
}
