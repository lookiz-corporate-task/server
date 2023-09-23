package com.corpotrate.lookiz.posts;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByOrderByTimestampDesc();

    List<PostEntity> findTop10ByOrderByLikeListDesc();

    List<PostEntity> findAllByInstaId(String instaId);

    @Query("SELECT DISTINCT p FROM PostEntity p LEFT JOIN p.likeList l ORDER BY SIZE(l) DESC")
    List<PostEntity> findTop10DistinctPosts(Pageable pageable);

}
