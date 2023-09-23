package com.corpotrate.lookiz.apis;

import com.corpotrate.lookiz.posts.PostService;
import com.corpotrate.lookiz.posts.dto.PostRequestDto;
import com.corpotrate.lookiz.posts.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public void postsFromInsta(@RequestBody List<PostRequestDto> postRequestDtos) {
        postService.postsFromInsta(postRequestDtos);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(@RequestBody String email) {
        return postService.getAllPosts(email);
    }

    @GetMapping("/rank")
    public ResponseEntity<List<PostResponseDto>> getTop10Posts(@RequestBody String email) {
        return postService.getTop10Posts(email);
    }

    @GetMapping("/my")
    public ResponseEntity<List<PostResponseDto>> getAllMy(@RequestBody String email) {
        return postService.getAllMy(email);
    }

}
