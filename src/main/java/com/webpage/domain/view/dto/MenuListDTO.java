package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.MenuListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuListDTO {

    private Integer id;
    private String menuName;
    private String menuTitle;
    private String menuComment;
    private int menuOrder;

    public MenuListEntity toEntity() {
        return MenuListEntity.builder().menuName(menuName).menuTitle(menuTitle)
                .menuComment(menuComment).menuOrder(menuOrder).build();
    }

}
