package com.bhumi.backend.service;

import com.bhumi.backend.dao.PostDAO;
import com.bhumi.backend.modal.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Transactional
    public Post addPost(Post post) {
        post.setDate(LocalDate.now());
        return postDAO.save(post);
    }

    @Transactional
    public Post updatePost(Post post) {
        post.setDate(LocalDate.now());
        return postDAO.save(post);
    }

    public void deletePostById(Long id) {
        postDAO.deleteById(id);
    }

    public boolean existsById(Long id) {
        return postDAO.existsById(id);
    }
}
