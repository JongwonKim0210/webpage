package com.webpage.domain.view.service.impl;

import com.webpage.domain.view.dto.FooterDTO;
import com.webpage.domain.view.dto.TabListDTO;
import com.webpage.domain.view.entity.TabListEntity;
import com.webpage.domain.view.vo.HeaderVO;
import com.webpage.domain.view.entity.CompanyInfo;
import com.webpage.domain.view.entity.MenuListEntity;
import com.webpage.domain.view.repository.CompanyInfoRepository;
import com.webpage.domain.view.repository.MenuListRepository;
import com.webpage.domain.view.repository.TabListRepository;
import com.webpage.domain.view.service.ViewService;
import com.webpage.domain.view.vo.ViewVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ViewServiceImpl implements ViewService {

    @Value("${notice}")
    Map<String, Integer> noticeInfo;

    private CompanyInfoRepository companyInfo;
    private MenuListRepository menuList;
    private TabListRepository tabList;

    @Override
    public void setCommonLayer(Model model, boolean isHeader, boolean isFooter) {
        if (isHeader) setHeader(model);
        if (isFooter) setFooter(model);
    }

    @Override
    public void setHeader(Model model) {
        List<MenuListEntity> entityList = menuList.findAllByOrderByMenuOrder();
        CompanyInfo company = companyInfo.findCompanyMainPhoneById(1);
        HeaderVO headerVO = new HeaderVO();
        headerVO.setMenuList(entityList);
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
        List<TabListEntity> entityList = tabList.findAllByMenuIdOrderByTabOrder(menuId);
        TabListEntity entity = tabList.findByIdAndMenuId(tabId, menuId);
        StringBuilder templatePath = new StringBuilder("template/");

        if (entity.convertToTabListDTO().getTemplateType() == 1) {
            templatePath.append("ImageTemplate");
        } else {
            templatePath.append("BoardTemplate");
        }

        List<ViewVO> viewVOList = new ArrayList<>();
        for (TabListEntity e : entityList) {
            TabListDTO tabListDTO = e.convertToTabListDTO();
            viewVOList.add(ViewVO.builder().menuId(menuId).tabId(tabListDTO.getId()).templateType(tabListDTO.getTemplateType()).build());
        }

        model.addAttribute("tabInfo", viewVOList);
        // TODO : 이상 탭 생성에 관한 데이터처리 완료(?) 이하 해당 탭에 대한 정보 조회해서 던져줄 것

        return templatePath.toString();
    }
}
