package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.MenuListEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HeaderVO {
    List<MenuListDTO> menuList;
    private String companyMainPhone;

    public void setMenuList(List<MenuListEntity> entityList) {
        menuList = new ArrayList<>();
        for (MenuListEntity e : entityList) {
            menuList.add(e.convertToTopMenuListDTO());
        }
    }
}
