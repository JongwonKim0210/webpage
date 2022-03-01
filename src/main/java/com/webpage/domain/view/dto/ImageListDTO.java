package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.ImageListEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageListDTO {

    private Long id;
    private Integer menuId;
    private Integer tabId;
    private String mimeType;
    private String name;
    private String path;
    private Long size;
    private String writeDate;

    public ImageListEntity toEntity() {
        return ImageListEntity.builder()
                .menuId(menuId).tabId(tabId).mimeType(mimeType)
                .name(name).path(path).size(size).build();
    }

}
