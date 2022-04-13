package com.webpage.domain.user.service;

import com.webpage.domain.user.dto.UserInfoDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    Map<String, Object> loginCheck(UserInfoDTO userInfoDTO, HttpServletRequest request);
    Map<String, Object> createUser(UserInfoDTO userInfoDTO);

}
