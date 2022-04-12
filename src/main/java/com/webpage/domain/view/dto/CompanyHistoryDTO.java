package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.CompanyHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyHistoryDTO {

    private Integer id;
    private String year;
    private String month;
    private String history;
    private String historyEn;

    public CompanyHistoryEntity toEntity() {
        return CompanyHistoryEntity.builder().year(year).month(month)
                .history(history).historyEn(historyEn).build();
    }
}
