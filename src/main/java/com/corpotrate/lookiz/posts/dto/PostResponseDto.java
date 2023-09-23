package com.corpotrate.lookiz.posts.dto;

import com.corpotrate.lookiz.posts.PostEntity;

import java.util.Date;

public class PostResponseDto {

    private String username;

    private Date timestamp;

    private String mediaUrl;

    private long likeCount;

    private boolean isLiked;

    public PostResponseDto(PostEntity post) {
        this.username = post.getUsername();
        this.timestamp = post.getTimestamp();
        this.mediaUrl = post.getMediaUrl();
        this.likeCount = post.getLikeList().size();
        this.isLiked = false;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
