package com.webpage.domain.user.entity;

import com.webpage.domain.user.dto.UserInfoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * auth : A, U, N
 * A : Admin Auth
 * U : Normal User Auth
 * N : None User Auth
 */
@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "userInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserinfoEntity {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Lob
    @Column(name = "auth", nullable = false)
    private String auth;

    @Column(name = "regDate", nullable = false)
    private Date regDate;

    @Column(name = "loginIP", length = 20)
    private String loginIP;

    @Column(name = "loginTime")
    private Date loginTime;

    public UserInfoDTO convertToUserInfoDTO() {
        return UserInfoDTO.builder()
                .id(id).password(password).auth(auth)
                .regDate(regDate).loginIP(loginIP)
                .loginTime(loginTime).build();
    }

}