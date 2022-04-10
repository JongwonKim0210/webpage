package com.webpage.domain.view.entity;

import com.webpage.domain.view.dto.BoardListDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "boardList")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Long id;

    @Column(name = "menuId", nullable = false)
    private Integer menuId;

    @Column(name = "tabId", nullable = false)
    private Integer tabId;

    @Column(name = "title", length = 200)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "writer", length = 12)
    private String writer;

    @Column(name = "writeDate", nullable = false)
    private Date writeDate;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    public BoardListDTO convertToBoardListDTO() {
        return BoardListDTO.builder().menuId(menuId).tabId(tabId).title(title)
                .content(content).writer(writer).writeDate(writeDate)
                .password(password).id(id).build();
    }

}