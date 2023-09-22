package com.corpotrate.lookiz.users;

import com.corpotrate.lookiz.users.dto.UserRequestDto;
import com.corpotrate.lookiz.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody UserRequestDto userRequestDto) {
        return userService.signin(userRequestDto);
    }



}

