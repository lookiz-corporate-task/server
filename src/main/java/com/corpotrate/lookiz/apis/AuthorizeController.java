package com.corpotrate.lookiz.apis;

import com.corpotrate.lookiz.integration.InstagramService;
import com.corpotrate.lookiz.integration.InstagramToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorize")
public class AuthorizeController {

    private final InstagramService instagramService;

    public AuthorizeController(InstagramService instagramService) {
        this.instagramService = instagramService;
    }

    @GetMapping("/instagram")
    public ResponseEntity<String> integration(
            @RequestHeader(value = "Host") String host,
            @RequestHeader(value = "X-Forwarded-Proto", required = false, defaultValue = "http") String protocol,
            @RequestParam String code
    ){
        String baseUrl = protocol + "://" + host;
        String redirectUri = baseUrl + "/authorize/instagram";

        InstagramToken token = instagramService.getInstagramToken(code, redirectUri);

        String htmlResponse = String.format(
                "<html>" +
                        "<head><script type=\"text/javascript\">" +
                        "window.opener.postMessage(\"%s\", \"*\");" +
                        "</script></head>" +
                        "</html>", token);

        return ResponseEntity.ok().body(htmlResponse);
    }
}
