package com.webpage.domain.view.entity;

import com.webpage.domain.view.dto.DetailImageListDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "detailImageList")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailImageListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Column(name = "imageId", nullable = false)
    private Long imageId;

    @Column(name = "mimeType", length = 50)
    private String mimeType;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "path", nullable = false, length = 256)
    private String path;

    @Column(name = "size")
    private Long size;

    @Column(name = "writeDate", nullable = false)
    private Date writeDate;

    public DetailImageListDTO convertToDetailImageListDTO() {
        return DetailImageListDTO.builder().id(id)
                .imageId(imageId).mimeType(mimeType)
                .name(name).path(path).size(size).writeDate(writeDate).build();
    }

}