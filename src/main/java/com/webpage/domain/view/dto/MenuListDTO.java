package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.MenuListEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuListDTO {

    private Integer id;
    private String menuName;
    private String menuComment;
    private int menuOrder;

    public MenuListEntity toEntity() {
        return MenuListEntity.builder().menuName(menuName)
                .menuComment(menuComment).menuOrder(menuOrder).build();
    }

}
