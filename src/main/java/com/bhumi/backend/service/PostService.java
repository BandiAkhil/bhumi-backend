package com.bhumi.backend.service;

import com.bhumi.backend.dao.PostDAO;
import com.bhumi.backend.repository.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> getAllPosts() {
        return postDAO.findAll();
    }

    public Post getPostById(Long id) {
        return postDAO.findById(id).orElseThrow(() -> new RuntimeException("Post by id " + id + " was not found"));
    }

    public boolean existsById(Long id) {
        return postDAO.existsById(id);
    }
}
