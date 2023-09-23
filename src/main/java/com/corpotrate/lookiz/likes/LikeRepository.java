package com.corpotrate.lookiz.likes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    LikeEntity findByPostIdAndUserId(Long postId, Long userId);

}
