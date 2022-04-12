package com.webpage.test;

import com.webpage.global.utils.ConvertUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @PostMapping("/test/check/session")
    public Map<String, Object> checkSession(HttpServletRequest request) throws IllegalAccessException {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("loginInfo");

        return ConvertUtils.convertToMap(userInfo);
    }

}
