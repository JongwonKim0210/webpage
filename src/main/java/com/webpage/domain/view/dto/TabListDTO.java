package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.TabListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TabListDTO {
    private Integer id;
    private Integer menuId;
    private String tabName;
    private String tabComment;
    private int tabOrder;
    private int templateType;
    private String useTab;

    public TabListEntity toEntity() {
        return TabListEntity.builder().id(id).useTab(useTab)
                .menuId(menuId).tabName(tabName).tabComment(tabComment)
                .tabOrder(tabOrder).templateType(templateType).build();
    }
}
