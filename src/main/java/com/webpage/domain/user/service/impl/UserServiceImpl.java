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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserInfoRepository userInfoRepository;

    @Override
    public Map<String, Object> loginCheck(UserInfoDTO userInfoDTO, HttpServletRequest request) {
        String hashPassword = HashUtils.SHA_3_512.getHashValue(userInfoDTO.getPassword());
        Optional<UserinfoEntity> userinfo = userInfoRepository.findAllByIdAndPassword(userInfoDTO.getId(), hashPassword);
        HttpStatus status = checkUserInfo(userInfoDTO.getId(), userinfo);

        if (status == HttpStatus.OK) {
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", userinfo.get().convertToUserInfoDTO());
            session.setAttribute("isAdmin", userinfo.get().convertToUserInfoDTO().getAuth());
            UserinfoEntity userinfoEntity = userinfo.get();
            userInfoDTO = userinfoEntity.convertToUserInfoDTO();
            userInfoDTO.setLoginIP(CommonUtils.getClientIP(request));
            userInfoRepository.save(userInfoDTO.toEntity());
        }

        return createResultValue(status, userInfoDTO);
    }

    @Override
    public Map<String, Object> createUser(UserInfoDTO userInfoDTO) {
        String hashPassword = HashUtils.SHA_3_512.getHashValue(userInfoDTO.getPassword());
        UserInfoDTO insertDto = UserInfoDTO.builder().id(userInfoDTO.getId()).password(hashPassword).auth("U").regDate(new Date()).build();
        userInfoRepository.save(insertDto.toEntity());
        Optional<UserinfoEntity> userinfo = userInfoRepository.findAllByIdAndPassword(userInfoDTO.getId(), hashPassword);
        HttpStatus status = checkUserInfo(userInfoDTO.getId(), userinfo);
        return createResultValue(status, insertDto);
    }

    private Map<String, Object> createResultValue(HttpStatus status, Object obj) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", status);
        resultMap.put("result", obj);
        return resultMap;
    }

    private HttpStatus checkUserInfo(String requestId, Optional<UserinfoEntity> selectResult) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if (!selectResult.isEmpty()) {
            status = requestId.equalsIgnoreCase(selectResult.get().convertToUserInfoDTO().getId()) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        }

        return status;
    }
}
