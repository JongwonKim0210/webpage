package com.webpage.domain.user.controller;

import com.webpage.domain.user.dto.UserInfoDTO;
import com.webpage.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class UserApiController {

    private UserService userService;

    @PostMapping("/login/check")
    public Map<String, Object> loginCheck(@RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request) {
        return userService.loginCheck(userInfoDTO, request);
    }

    @PostMapping("/login/create")
    public Map<String, Object> createUser(@RequestBody UserInfoDTO userInfoDTO) {
        return userService.createUser(userInfoDTO);
    }

}
