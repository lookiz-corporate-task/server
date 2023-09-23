package com.corpotrate.lookiz.posts;

import com.corpotrate.lookiz.likes.LikeRepository;
import com.corpotrate.lookiz.posts.dto.PostRequestDto;
import com.corpotrate.lookiz.posts.dto.PostResponseDto;
import com.corpotrate.lookiz.users.UserEntity;
import com.corpotrate.lookiz.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void postsFromInsta(PostRequestDto postRequestDto) {
        System.out.println(postRequestDto);
        String UTCTime = postRequestDto.getTimestamp();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            Date date = dateFormat.parse(UTCTime);
            PostEntity post = new PostEntity(postRequestDto, date);
            System.out.println(post);
            postRepository.save(post);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<PostResponseDto>> getAllPosts(String email) {
        UserEntity foundUser = userRepository.findByEmail(email);

        List<PostEntity> allPosts = postRepository.findAllByOrderByTimestampDesc();

        List<PostResponseDto> postResponseDtos = allPosts.stream()
                .map((post) -> new PostResponseDto(post, post.getUsername(), foundUser.getId()))
                .collect(Collectors.toList());;

        return ResponseEntity.ok().body(postResponseDtos);

    }

    public ResponseEntity<List<PostResponseDto>> getTop10Posts(String email) {
        UserEntity foundUser = userRepository.findByEmail(email);

        List<PostEntity> allPosts = postRepository.findTop10ByOrderByLikeListDesc();

        List<PostResponseDto> postResponseDtos = allPosts.stream()
                .map((post) -> new PostResponseDto(post, post.getUsername(), foundUser.getId()))
                .collect(Collectors.toList());;

        return ResponseEntity.ok().body(postResponseDtos);
    }

    public ResponseEntity<List<PostResponseDto>> getAllMy(String email) {
        UserEntity foundUser = userRepository.findByEmail(email);

        if (foundUser == null || foundUser.getInstaId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<PostEntity> allPosts = postRepository.findAllByInstaId(foundUser.getInstaId());

        List<PostResponseDto> postResponseDtos = allPosts.stream()
                .map((post) -> new PostResponseDto(post, post.getUsername(), foundUser.getId()))
                .collect(Collectors.toList());;

        return ResponseEntity.ok().body(postResponseDtos);
    }
}