package com.bhumi.backend.dto;

import java.time.LocalDate;

public class ForumDTO {
    
    private Long id;
    private String title;
    private String body;
    private Long userId;
    private String username;
    private LocalDate date;
    
    public ForumDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "ForumDTO [body=" + body + ", date=" + date + ", id=" + id + ", title=" + title + ", userId=" + userId
                + ", username=" + username + "]";
    }
}
