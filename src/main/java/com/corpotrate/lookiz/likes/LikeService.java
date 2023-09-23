package com.corpotrate.lookiz.likes;

import com.corpotrate.lookiz.posts.PostEntity;
import com.corpotrate.lookiz.posts.PostRepository;
import com.corpotrate.lookiz.users.UserEntity;
import com.corpotrate.lookiz.users.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public LikeService(UserRepository userRepository, PostRepository postRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    @Transactional
    public ResponseEntity<HttpStatus> createLike(Long postId, String email) {
        PostEntity foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        UserEntity foundUser = userRepository.findByEmail(email);
        
        //이미 좋아요를 했다면
        if (likeRepository.findByPostIdAndUserId(postId, foundUser.getId()) != null) {
            throw new IllegalArgumentException("이미 좋아요 등록이 되었습니다.");
        }

        LikeEntity like = new LikeEntity(foundPost, foundUser);
        likeRepository.save(like);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteLike(Long postId, String email) {
        PostEntity foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        UserEntity foundUser = userRepository.findByEmail(email);
        if (foundPost.getUser().getId() == foundUser.getId()) {
            throw new IllegalArgumentException("본인 작성한 글은 좋아요 할 수 없습니다.");
        }

        Long likeId = likeRepository.findByPostIdAndUserId(postId, foundUser.getId());

        likeRepository.deleteById(likeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
