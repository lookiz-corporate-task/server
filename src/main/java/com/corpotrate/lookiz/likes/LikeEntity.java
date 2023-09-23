package com.corpotrate.lookiz.likes;

import com.corpotrate.lookiz.posts.PostEntity;
import com.corpotrate.lookiz.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`like`")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    public LikeEntity(PostEntity foundPost, UserEntity foundUser) {
        this.user = foundUser;
        this.post = foundPost;
    }
}
