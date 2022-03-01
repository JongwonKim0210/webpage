package com.webpage.domain.view.service.impl;

import com.webpage.domain.view.dto.FooterDTO;
import com.webpage.domain.view.dto.ImageListDTO;
import com.webpage.domain.view.dto.TabListDTO;
import com.webpage.domain.view.entity.ImageListEntity;
import com.webpage.domain.view.entity.TabListEntity;
import com.webpage.domain.view.repository.ImageListRepository;
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

    @Value("#{${notice}}")
    Map<String, Integer> noticeInfo;

    private CompanyInfoRepository companyInfo;
    private MenuListRepository menuList;
    private TabListRepository tabList;
    private ImageListRepository imageList;

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
        setCommonLayer(model, true, true);
//        noticeInfo;   // 공지사항 목록 표시(메인화면에 표시할 항목임 / 나중에 menuId, tabId 확정되면 properties 파일 수정해야됨
    }

    @Override
    public String setViewPage(Model model, int menuId, int tabId) {
        setCommonLayer(model, true, true);

        List<TabListEntity> entityList = tabList.findAllByMenuIdOrderByTabOrder(menuId);
        TabListEntity entity = tabList.findByIdAndMenuId(tabId, menuId);
        StringBuilder templatePath = new StringBuilder("template/");
        // TODO : DB 데이터가 없어서 현재 예외 발생 중
        switch (entity.convertToTabListDTO().getTemplateType()) {
            case 0 -> templatePath.append("CompanyInfo");
            case 1 -> templatePath.append("ImageTemplate");
            case 2 -> templatePath.append("BoardTemplate");
        }

        List<ViewVO> viewVOList = new ArrayList<>();
        for (TabListEntity e : entityList) {
            TabListDTO tabListDTO = e.convertToTabListDTO();
            viewVOList.add(ViewVO.builder().menuId(menuId).tabId(tabListDTO.getId()).tabName(tabListDTO.getTabName()).templateType(tabListDTO.getTemplateType()).build());
        }

        model.addAttribute("menuId", menuId);
        model.addAttribute("tabList", viewVOList);
        model.addAttribute("tabData", getTabData(menuId, tabId, entity.convertToTabListDTO().getTemplateType()));
        return templatePath.toString();
    }

    private List getTabData(int menuId, int tabId, int templateType) {
        List mainList = mainList = new ArrayList<List<?>>();;
        if (templateType == 1) {
            List<ImageListEntity> entities = imageList.findAllByMenuIdAndTabId(menuId, tabId);
            for (int i = 0; i < entities.size(); i++) {
                List<ImageListDTO> images = new ArrayList<>();
                if ((i + 1) % 4 == 0) {
                    mainList.add(images);
                    images = new ArrayList<>();
                }

                images.add(entities.get(i).convertToImageListDTO());
                // TODO : 이미지 경로 확인해서 API 연결시켜놓기
            }
        } else if (templateType == 2) {

        }

        return mainList;
    }
}
