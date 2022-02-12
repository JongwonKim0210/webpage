package com.webpage.domain.view.service.impl;

import com.webpage.domain.view.dto.FooterDTO;
import com.webpage.domain.view.dto.HeaderVO;
import com.webpage.domain.view.entity.CompanyInfo;
import com.webpage.domain.view.entity.MenuListEntity;
import com.webpage.domain.view.repository.CompanyInfoRepository;
import com.webpage.domain.view.repository.TopMenuListRepository;
import com.webpage.domain.view.service.ViewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class ViewServiceImpl implements ViewService {

    private CompanyInfoRepository companyInfo;
    private TopMenuListRepository topMenuList;

    @Override
    public void setCommonLayer(Model model, boolean isHeader, boolean isFooter) {
        if (isHeader) setHeader(model);
        if (isFooter) setFooter(model);
    }

    @Override
    public void setHeader(Model model) {
        List<MenuListEntity> menuList = topMenuList.findAllByOrderByMenuOrder();
        CompanyInfo company = companyInfo.findCompanyMainPhoneById(1);
        HeaderVO headerVO = new HeaderVO();
        headerVO.setMenuList(menuList);
        headerVO.setCompanyMainPhone(company.convertToDTO().getCompanyMainPhone());
        model.addAttribute("header", headerVO);
    }

    @Override
    public void setFooter(Model model) {
        CompanyInfo company = companyInfo.findAllById(1);
        FooterDTO footerDTO = company.convertToDTO();
        model.addAttribute("footer", footerDTO);
    }

    @Override
    public void setNoticeList(Model model) {

    }

    @Override
    public String setViewPage(Model model, int menuId, int tabId) {
        return null;
    }
}
