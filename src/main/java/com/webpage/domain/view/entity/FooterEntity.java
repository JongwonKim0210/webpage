package com.webpage.domain.view.entity;

import com.webpage.domain.view.dto.FooterDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "companyInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FooterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private long id;

    @Column(name = "companyName", nullable = false, length = 40)
    private String companyName;

    @Column(name = "companyLeader", nullable = false, length = 10)
    private String companyLeader;

    @Column(name = "companyAddress", nullable = false, length = 500)
    private String companyAddress;

    @Column(name = "companyRegNum", nullable = false, length = 12)
    private String companyRegNum;

    @Column(name = "companyMainPhone", nullable = false, length = 16)
    private String companyMainPhone;

    @Column(name = "companyEmail", nullable = false, length = 80)
    private String companyEmail;

    @Column(name = "companyFax", nullable = false, length = 16)
    private String companyFax;

    @Column(name = "companyRegDate", nullable = false)
    private String companyRegDate;

    @Column(name = "updateDate", nullable = false)
    private String updateDate;

    public FooterDTO convertToDTO() {
        return FooterDTO.builder()
                .companyName(companyName).companyLeader(companyLeader)
                .companyAddress(companyAddress).companyRegNum(companyRegNum)
                .companyMainPhone(companyMainPhone).companyEmail(companyEmail)
                .companyFax(companyFax).companyRegDate(companyRegDate)
                .updateDate(updateDate).build();
    }
}
