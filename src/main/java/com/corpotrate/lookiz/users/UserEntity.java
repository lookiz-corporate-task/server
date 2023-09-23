package com.corpotrate.lookiz.users;

import com.corpotrate.lookiz.users.dto.InstaRequestDto;
import com.corpotrate.lookiz.users.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long instaId;

    public UserEntity(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.nickname = requestDto.getNickname();
    }

    public void connectInsta(InstaRequestDto instaRequestDto) {
        this.instaId = instaRequestDto.getInstaId();
    }
}
