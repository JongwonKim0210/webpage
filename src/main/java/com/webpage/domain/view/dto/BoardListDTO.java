package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.BoardListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDTO {

    private Long id;
    private Integer menuId;
    private Integer tabId;
    private String title;
    private String content;
    private String writer;
    private Date writeDate;
    private String password;

    public BoardListEntity toEntity() {
        return BoardListEntity.builder()
                .menuId(menuId).tabId(tabId).title(title)
                .content(content).writer(writer).writeDate(writeDate)
                .password(password).build();
    }
}
