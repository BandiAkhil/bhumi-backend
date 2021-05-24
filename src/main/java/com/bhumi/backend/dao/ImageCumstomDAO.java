package com.bhumi.backend.dao;

import java.util.List;

import com.bhumi.backend.entity.Image;

public interface ImageCumstomDAO {
    List<Image> findAllByPost(Long postId);
    List<Image> findByUser(Long userId);
}
