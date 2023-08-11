package com.jun.union.controller;

import com.jun.union.dto.ResponseDataDTO;
import com.jun.union.service.user.UserDTO;
import com.jun.union.service.user.UserSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserSignService userSignService;

    @PostMapping("/register")
    public ResponseDataDTO<UserDTO> register(@RequestBody UserDTO dto) {
        return userSignService.register(dto);
    }

    @PostMapping("/login")
    public ResponseDataDTO<UserDTO> login(@RequestBody UserDTO dto) {
        return userSignService.login(dto);
    }

    @PostMapping("/logout")
    public ResponseDataDTO<UserDTO> logout(@RequestBody UserDTO dto) {
        return userSignService.logout(dto);
    }

    @PostMapping("/change/password")
    public ResponseDataDTO<UserDTO> changePassword(@RequestBody UserDTO dto) {
        return userSignService.changePassword(dto);
    }

    @PostMapping("/issue/temporary-password")
    public ResponseDataDTO<UserDTO> issueTemporaryPassword(@RequestBody UserDTO dto) {
        return userSignService.issueTemporaryPassword(dto);
    }
}
