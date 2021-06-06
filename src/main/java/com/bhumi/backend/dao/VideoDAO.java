package com.bhumi.backend.dao;

import java.util.List;

import com.bhumi.backend.entity.Video;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VideoDAO extends JpaRepository<Video, Long> {
    List<Video> findAllByOrderByUploadedAsc();
    List<Video> findByIshaVideoTrueOrderByUploadedAsc();
    List<Video> findByHomeVideoTrueOrderByUploadedAsc();
}
