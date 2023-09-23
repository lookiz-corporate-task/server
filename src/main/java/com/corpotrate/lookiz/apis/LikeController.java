package com.corpotrate.lookiz.apis;

import com.corpotrate.lookiz.likes.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseEntity<HttpStatus> createLike(@PathVariable Long postId, @RequestHeader(value = "Authorization") String email) {
        System.out.println(email);
        return likeService.createLike(postId, email);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<HttpStatus> deleteLike(@PathVariable Long postId, @RequestHeader(value = "Authorization") String email) {
        System.out.println(email);
        return likeService.deleteLike(postId, email);
    }

}
