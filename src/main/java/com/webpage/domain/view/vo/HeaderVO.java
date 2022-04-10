package com.webpage.domain.view.vo;

import com.webpage.domain.view.dto.MenuListDTO;
import com.webpage.domain.view.dto.TabListDTO;
import com.webpage.domain.view.entity.MenuListEntity;
import com.webpage.domain.view.entity.TabListEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HeaderVO {
    private String companyMainPhone;
    List<Map<String, Object>> menuList;
}
