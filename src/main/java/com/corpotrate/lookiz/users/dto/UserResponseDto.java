package com.corpotrate.lookiz.users.dto;

import com.corpotrate.lookiz.users.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;

    private String nickname;

    public UserResponseDto(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }

}
