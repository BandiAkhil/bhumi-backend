package com.bhumi.backend.dto;

public class VoteDTO {
    
    private Long postId;
    private Long commentId;
    private Long forumId;
    private Long forumAnswerId;
    private Long userId;
    
    public VoteDTO() {
    }

    public VoteDTO(Long postId, Long commentId, Long forumId, Long forumAnswerId, Long userId) {
        this.postId = postId;
        this.commentId = commentId;
        this.forumId = forumId;
        this.forumAnswerId = forumAnswerId;
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    public Long getForumAnswerId() {
        return forumAnswerId;
    }

    public void setForumAnswerId(Long forumAnswerId) {
        this.forumAnswerId = forumAnswerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VoteDTO [commentId=" + commentId + ", forumAnswerId=" + forumAnswerId + ", forumId=" + forumId
                + ", postId=" + postId + ", userId=" + userId + "]";
    }
}
