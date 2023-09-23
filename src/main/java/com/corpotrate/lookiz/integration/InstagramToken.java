package com.corpotrate.lookiz.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstagramToken {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("user_id")
    private String userId;

    @Override
    public String toString() {
        return "InstagramToken{" +
                "accessToken='" + accessToken + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
