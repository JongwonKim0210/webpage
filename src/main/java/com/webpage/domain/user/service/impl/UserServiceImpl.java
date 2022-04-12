package com.webpage.domain.user.service.impl;

import com.webpage.domain.user.dto.UserInfoDTO;
import com.webpage.domain.user.entity.UserinfoEntity;
import com.webpage.domain.user.repository.UserInfoRepository;
import com.webpage.domain.user.service.UserService;
import com.webpage.global.utils.CommonUtils;
import com.webpage.global.utils.HashUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserInfoRepository userInfoRepository;

    @Override
    public ResponseEntity loginCheck(UserInfoDTO userInfoDTO, HttpServletRequest request) {
        String hashPassword = HashUtils.SHA_3_512.getHashValue(userInfoDTO.getPassword());
        Optional<UserinfoEntity> userinfo = userInfoRepository.findAllByIdAndPassword(userInfoDTO.getId(), hashPassword);
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if (!userinfo.isEmpty()) {
            status = userInfoDTO.getId().equalsIgnoreCase(userinfo.get().convertToUserInfoDTO().getId()) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        }

        if (status == HttpStatus.OK) {
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", userinfo.get().convertToUserInfoDTO());
            UserinfoEntity userinfoEntity = userinfo.get();
            userInfoDTO = userinfoEntity.convertToUserInfoDTO();
            userInfoDTO.setLoginIP(CommonUtils.getClientIP(request));
            userInfoRepository.save(userInfoDTO.toEntity());
        }

        return new ResponseEntity<>(status);
    }
}
