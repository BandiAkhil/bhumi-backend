package com.bhumi.backend.dto;

import java.time.LocalDate;

public class CommentDTO {

    private Long id;
    private String text;
    private Long postId;
    private Long parentCommentId;
    private Long userId;
    private LocalDate updated;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String text, Long postId, Long parentCommentId, Long userId, LocalDate updated) {
        this.id = id;
        this.text = text;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.userId = userId;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }
}
