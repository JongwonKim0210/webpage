package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.ImageListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageListDTO {

    private Long id;
    private Integer menuId;
    private Integer tabId;
    private String mimeType;
    private String name;
    private String path;
    private Long size;
    private Date writeDate;

    public ImageListEntity toEntity() {
        return ImageListEntity.builder()
                .menuId(menuId).tabId(tabId).mimeType(mimeType)
                .name(name).path(path).size(size).writeDate(writeDate).build();
    }

}
