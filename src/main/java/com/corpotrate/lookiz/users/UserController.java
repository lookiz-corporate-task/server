package com.corpotrate.lookiz.users;

import com.corpotrate.lookiz.users.dto.InstaRequestDto;
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
    public ResponseEntity<String> signin(@RequestBody UserRequestDto userRequestDto) {
        return userService.signin(userRequestDto);
    }

    @PostMapping("/connect")
    public ResponseEntity<String> connectInsta(@RequestBody InstaRequestDto instaRequestDto) {
        return userService.connectInsta(instaRequestDto);
    }



}

