package com.corpotrate.lookiz.users;

import com.corpotrate.lookiz.users.dto.InstaRequestDto;
import com.corpotrate.lookiz.users.dto.LoginRequestDto;
import com.corpotrate.lookiz.users.dto.UserRequestDto;
import com.corpotrate.lookiz.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<UserResponseDto> signup(UserRequestDto userRequestDto) {

        UserEntity foundUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (foundUser != null) {
            throw new ResponseStatusException(HttpStatus.valueOf(409), "중복된 이메일이 존재합니다");
        }

        UserEntity user = new UserEntity(userRequestDto);

        userRepository.save(user);
        UserResponseDto responseDto = new UserResponseDto(user);
        return ResponseEntity.ok(responseDto);
    }


    public ResponseEntity<String> signin(LoginRequestDto loginRequestDto) {
        UserEntity foundUser = userRepository.findByEmail(loginRequestDto.getEmail());
        if (foundUser == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(409), "유저가 존재하지 않습니다");
        }

        if ((foundUser.getEmail().equals(loginRequestDto.getEmail())) &&
                ((foundUser.getPassword().equals(loginRequestDto.getPassword())))) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.badRequest().body("fail");
        }
    }

    public ResponseEntity<String> connectInsta(InstaRequestDto instaRequestDto) {
        UserEntity foundUser = userRepository.findByEmail(instaRequestDto.getEmail());
        if (foundUser == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(409), "유저가 존재하지 않습니다");
        }

        foundUser.connectInsta(instaRequestDto);
        userRepository.save(foundUser);
        return ResponseEntity.ok().body("success");
    }

}
