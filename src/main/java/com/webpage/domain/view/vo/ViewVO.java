package com.webpage.domain.view.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ViewVO {

    private int menuId;
    private int tabId;
    private int templateType;

}
