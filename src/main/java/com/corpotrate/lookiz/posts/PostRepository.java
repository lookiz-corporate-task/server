package com.corpotrate.lookiz.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByOrderByTimestampDesc();

    List<PostEntity> findTop10ByOrderByLikeListDesc();

    List<PostEntity> findAllByInstaId(String instaId);
}
