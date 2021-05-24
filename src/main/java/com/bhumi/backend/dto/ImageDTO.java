package com.bhumi.backend.dto;

import java.util.Arrays;

public class ImageDTO {
    
    private String type;
    private byte[] data;
    private Long postId;
    private Long userId;
    
    public ImageDTO() {
    }

    public ImageDTO(String type, byte[] data, Long postId, Long userId) {
        this.type = type;
        this.data = data;
        this.postId = postId;
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "ImageDTO [data=" + Arrays.toString(data) + ", postId=" + postId + ", type=" + type + ", userId="
                + userId + "]";
    }

}
