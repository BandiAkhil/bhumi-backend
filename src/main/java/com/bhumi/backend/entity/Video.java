package com.bhumi.backend.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ishaVideo;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean homeVideo;
    @Column(nullable = false, updatable = false)
    private LocalDate uploaded;

    public Video() {
    }

    public Video(Long id, String name, String url, boolean ishaVideo, boolean homeVideo, LocalDate uploaded) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.ishaVideo = ishaVideo;
        this.homeVideo = homeVideo;
        this.uploaded = uploaded;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIshaVideo() {
        return ishaVideo;
    }

    public void setIshaVideo(boolean ishaVideo) {
        this.ishaVideo = ishaVideo;
    }

    public boolean isHomeVideo() {
        return homeVideo;
    }

    public void setHomeVideo(boolean homeVideo) {
        this.homeVideo = homeVideo;
    }

    public LocalDate getUploaded() {
        return uploaded;
    }

    public void setUploaded(LocalDate uploaded) {
        this.uploaded = uploaded;
    }
}
