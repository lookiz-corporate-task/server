package com.corpotrate.lookiz.likes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Long findByPostIdAndUserId(Long postId, Long userId);

}
