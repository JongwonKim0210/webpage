package com.webpage.test;

import com.webpage.global.utils.HashUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PasswordGenApiController {

    @PostMapping("/api/generate/password")
    public Map<String, Object> generatePassword(@RequestBody Map<String, Object> requestPassword) {
        String hashPassword = HashUtils.SHA_3_512.getHashValue(requestPassword.get("password").toString());
        requestPassword.put("hashPassword", hashPassword);
        return requestPassword;
    }

}
