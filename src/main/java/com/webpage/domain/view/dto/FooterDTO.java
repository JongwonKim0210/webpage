package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.FooterEntity;
import lombok.Builder;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Builder
public class FooterDTO {

    private String companyName;
    private String companyLeader;
    private String companyAddress;
    private String companyRegNum;
    private String companyMainPhone;
    private String companyEmail;
    private String companyFax;
    private String companyRegDate;
    private String updateDate;

    public FooterEntity toEntity() {
        return FooterEntity.builder()
                .companyName(companyName).companyLeader(companyLeader)
                .companyAddress(companyAddress).companyRegNum(companyRegNum)
                .companyMainPhone(companyMainPhone).companyEmail(companyEmail)
                .companyFax(companyFax).companyRegDate(companyRegDate)
                .updateDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .build();
    }
}
