package com.webpage.domain.view.entity;

import com.webpage.domain.view.dto.MenuListDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "menuList")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Column(name = "menuName", nullable = false, length = 50)
    private String menuName;

    @Column(name = "menuComment", length = 500)
    private String menuComment;

    @Column(name = "menuOrder", nullable = false)
    private Integer menuOrder;

    public MenuListDTO convertToMenuListDTO() {
        return MenuListDTO.builder().id(id).menuName(menuName)
                .menuComment(menuComment).menuOrder(menuOrder).build();
    }

}