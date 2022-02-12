package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.TabListEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TabListDTO {
    private Integer id;
    private Integer menuId;
    private String tabName;
    private String tabComment;
    private int tabOrder;
    private int templateType;

    public TabListEntity toEntity() {
        return TabListEntity.builder().id(id)
                .menuId(menuId).tabName(tabName).tabComment(tabComment)
                .tabOrder(tabOrder).templateType(templateType).build();
    }
}
