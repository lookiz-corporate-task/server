package com.corpotrate.lookiz.posts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String instaId;

    private String username;

    private String timestamp;

    @JsonProperty("media_url")
    private String mediaUrl;

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "instaId='" + instaId + '\'' +
                ", username='" + username + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                '}';
    }
}
