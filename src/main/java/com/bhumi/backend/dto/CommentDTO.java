package com.bhumi.backend.dto;

import java.time.LocalDate;

public class CommentDTO {

    private Long id;
    private String text;
    private Long postId;
    private Long userId;
    private String username;
    private LocalDate updated;
    private Long parentCommentId;
    private int commentDepth;
    private int childCount;

    public CommentDTO() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public int getCommentDepth() {
        return commentDepth;
    }

    public void setCommentDepth(int commentDeapth) {
        this.commentDepth = commentDeapth;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    @Override
    public String toString() {
        return "CommentDTO [childCount=" + childCount + ", commentDepth=" + commentDepth + ", id=" + id
                + ", parentCommentId=" + parentCommentId + ", postId=" + postId + ", text=" + text + ", updated="
                + updated + ", userId=" + userId + ", username=" + username + "]";
    }
}
