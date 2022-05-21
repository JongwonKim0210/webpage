package com.webpage.domain.view.dto;

import com.webpage.domain.view.entity.FooterEntity;
import com.webpage.domain.view.vo.ViewVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private List<ViewVO> companyViewList;
    private List<MenuListDTO> menuViewList;

    public FooterEntity toEntity() {
        return FooterEntity.builder()
                .companyName(companyName).companyLeader(companyLeader)
                .companyAddress(companyAddress).companyRegNum(companyRegNum)
                .companyMainPhone(companyMainPhone).companyEmail(companyEmail)
                .companyFax(companyFax).companyRegDate(companyRegDate)
                .updateDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .build();
    }

    public void setCompanyViewList(List<ViewVO> companyViewList) {
        this.companyViewList = companyViewList;
    }

    public void setMenuViewList(List<MenuListDTO> menuViewList) {
        this.menuViewList = menuViewList;
    }
}
