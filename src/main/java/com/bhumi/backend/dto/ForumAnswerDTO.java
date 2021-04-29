package com.bhumi.backend.dto;

import java.time.LocalDate;

public class ForumAnswerDTO {
    
    private Long id;
    private String body;
    private Long userId;
    private String username;
    private LocalDate date;
    private Long forumId;

    public ForumAnswerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    @Override
    public String toString() {
        return "ForumAnswerDTO [body=" + body + ", date=" + date + ", forumId=" + forumId + ", id=" + id + ", userId="
                + userId + ", username=" + username + "]";
    }
}