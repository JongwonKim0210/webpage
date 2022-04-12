package com.webpage.domain.view.entity;

import com.webpage.domain.view.dto.CompanyHistoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "companyHistory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Column(name = "year", nullable = false, length = 4)
    private String year;

    @Column(name = "month", nullable = false, length = 2)
    private String month;

    @Column(name = "history", nullable = false, length = 2000)
    private String history;

    @Column(name = "history_en", length = 2000)
    private String historyEn;

    public CompanyHistoryDTO convertToCompanyHistoryDTO() {
        return CompanyHistoryDTO.builder().year(year).month(month)
                .history(history).historyEn(historyEn).id(id).build();
    }
}