package com.corpotrate.lookiz.posts.dto;

import com.corpotrate.lookiz.posts.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long id;

    private String username;

    private String nickname;

    private Date timestamp;

    private String mediaUrl;

    private long likeCount;

    private boolean isLiked;

    public PostResponseDto(PostEntity post,String nickname) {
        this.id = post.getId();
        this.nickname = nickname;
        this.username = post.getUsername();
        this.timestamp = post.getTimestamp();
        this.mediaUrl = post.getMediaUrl();
        //this.likeCount = post.getLikeList().size();
        this.isLiked = false;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
