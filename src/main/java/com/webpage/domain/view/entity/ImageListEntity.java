package com.webpage.domain.view.entity;

import com.webpage.domain.view.dto.ImageListDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "imageList")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Long id;

    @Column(name = "menuId", nullable = false)
    private Integer menuId;

    @Column(name = "tabId", nullable = false)
    private Integer tabId;

    @Column(name = "mimeType", length = 50)
    private String mimeType;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "path", nullable = false, length = 256)
    private String path;

    @Column(name = "size")
    private Long size;

    @Column(name = "writeDate", nullable = false)
    private String writeDate;

    public ImageListDTO convertToImageListDTO() {
        return ImageListDTO.builder()
                .id(id).menuId(menuId).tabId(tabId)
                .mimeType(mimeType).name(name).path(path)
                .size(size).writeDate(writeDate).build();
    }

}
