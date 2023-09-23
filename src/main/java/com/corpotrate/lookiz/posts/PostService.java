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

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;


    public void postsFromInsta(List<PostRequestDto> postRequestDtos) {

        for (PostRequestDto postRequestDto : postRequestDtos) {
            String UTCTime = postRequestDto.getTimestamp();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            try {
                Date date = dateFormat.parse(UTCTime);
                PostEntity post = new PostEntity(postRequestDto, date);
                postRepository.save(post);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public ResponseEntity<List<PostResponseDto>> getAllPosts(String email) {

        UserEntity foundUser = userRepository.findByEmail(email);

        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        List<PostEntity> allPosts = postRepository.findAllByOrderByTimestampDesc();

        return getListResponseEntity(postResponseDtos, foundUser, allPosts);

    }

    public ResponseEntity<List<PostResponseDto>> getTop10Posts(String email) {

        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        UserEntity foundUser = userRepository.findByEmail(email);

        List<PostEntity> allPosts = postRepository.findTop10ByOrderByLikeListDesc();

        return getListResponseEntity(postResponseDtos, foundUser, allPosts);

    }

    public ResponseEntity<List<PostResponseDto>> getAllMy(String email) {

        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        UserEntity foundUser = userRepository.findByEmail(email);
        if (foundUser == null || foundUser.getInstaId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<PostEntity> allPosts = postRepository.findAllByInstaId(foundUser.getInstaId());

        return getListResponseEntity(postResponseDtos, foundUser, allPosts);

    }

    private ResponseEntity<List<PostResponseDto>> getListResponseEntity(List<PostResponseDto> postResponseDtos, UserEntity foundUser, List<PostEntity> allPosts) {

        if (allPosts == null) {
            return ResponseEntity.badRequest().body(null);
        }

        for (PostEntity post : allPosts) {
            PostResponseDto responseDto = new PostResponseDto(post, foundUser.getNickname());
            responseDto.setLiked(likeRepository.findByPostIdAndUserId(post.getId(), foundUser.getId()) != null);
            postResponseDtos.add(responseDto);
        }

        return ResponseEntity.ok().body(postResponseDtos);

    }

}