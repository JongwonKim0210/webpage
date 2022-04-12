package com.webpage.domain.user.service;

import com.webpage.domain.user.dto.UserInfoDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity loginCheck(UserInfoDTO userInfoDTO, HttpServletRequest request);

}
