package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.DetailImageListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailImageListDTO {

    private Integer id;
    private Long imageId;
    private String mimeType;
    private String name;
    private String path;
    private Long size;
    private Date writeDate;

    public DetailImageListEntity toEntity() {
        return DetailImageListEntity.builder()
                .imageId(imageId).mimeType(mimeType)
                .name(name).path(path).size(size).writeDate(writeDate).build();
    }
}
