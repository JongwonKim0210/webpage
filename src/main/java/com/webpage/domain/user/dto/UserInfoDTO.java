package com.webpage.domain.user.dto;

import com.webpage.domain.user.entity.UserinfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private String id;
    private String password;
    private String auth;
    private Date regDate;
    private String loginIP;
    private Date loginTime;

    public UserinfoEntity toEntity() {
        return UserinfoEntity.builder()
                .id(id).password(password).auth(auth)
                .regDate(regDate).loginIP(loginIP)
                .loginTime(loginTime).build();
    }

    public void setLoginIP(String ip) {
        this.loginIP = ip;
    }
}
