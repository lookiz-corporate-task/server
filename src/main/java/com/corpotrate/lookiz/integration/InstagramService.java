package com.corpotrate.lookiz.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class InstagramService {


    public InstagramToken getInstagramToken(String code, String redirectUri){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "402165171907832");
        formData.add("client_secret", "13c1f9e2317060a5642ba94234ae084b");
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", redirectUri);
        formData.add("code", code);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        // HTTP Entity 생성
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        // API 호출
        return restTemplate.postForEntity("https://api.instagram.com/oauth/access_token", request, InstagramToken.class).getBody();
    }
}

