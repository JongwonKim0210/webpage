package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Long> {

    CompanyInfo findAllById(long idx);
    CompanyInfo findCompanyMainPhoneById(long idx);
}
