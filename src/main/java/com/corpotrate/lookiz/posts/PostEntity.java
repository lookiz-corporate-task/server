package com.corpotrate.lookiz.posts;

import com.corpotrate.lookiz.likes.LikeEntity;
import com.corpotrate.lookiz.posts.dto.PostRequestDto;
import com.corpotrate.lookiz.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String instaId;

    @Column
    private Date timestamp;

    @Column(columnDefinition = "TEXT", unique = true)
    private String mediaUrl;

    @Column
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post", fetch = FetchType.EAGER)
    private List<LikeEntity> likeList = new ArrayList<>();

    public PostEntity(PostRequestDto postRequestDto, Date timestamp) {
        this.instaId = postRequestDto.getInstaId();
        this.username = postRequestDto.getUsername();
        this.timestamp = timestamp;
        this.mediaUrl = postRequestDto.getMediaUrl();
    }
}
