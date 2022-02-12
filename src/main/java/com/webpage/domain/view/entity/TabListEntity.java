package com.webpage.domain.view.entity;

import com.webpage.domain.view.dto.TabListDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "tabList")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TabListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Column(name = "menuId", nullable = false)
    private Integer menuId;

    @Column(name = "tabName", nullable = false, length = 50)
    private String tabName;

    @Column(name = "tabComment", length = 500)
    private String tabComment;

    @Column(name = "tabOrder", nullable = false)
    private int tabOrder;

    @Column(name = "templateType", nullable = false)
    private int templateType;

    public TabListDTO convertToTabListDTO() {
        return TabListDTO.builder().id(id)
                .menuId(menuId).tabName(tabName).tabComment(tabComment)
                .tabOrder(tabOrder).templateType(templateType).build();
    }
}