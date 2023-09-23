package com.corpotrate.lookiz.apis;

import com.corpotrate.lookiz.users.UserService;
import com.corpotrate.lookiz.users.dto.InstaRequestDto;
import com.corpotrate.lookiz.users.dto.LoginRequestDto;
import com.corpotrate.lookiz.users.dto.UserRequestDto;
import com.corpotrate.lookiz.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> signin(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.signin(loginRequestDto);
    }

    @PostMapping("/connect")
    public ResponseEntity<String> connectInsta(@RequestBody InstaRequestDto instaRequestDto) {
        return userService.connectInsta(instaRequestDto);
    }



}

