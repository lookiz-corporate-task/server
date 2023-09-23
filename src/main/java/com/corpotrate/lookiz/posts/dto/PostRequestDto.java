package com.corpotrate.lookiz.posts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    @JsonProperty("id")
    private Long instaId;

    private String username;

    private String timestamp;

    @JsonProperty("media_url")
    private String mediaUrl;

}
