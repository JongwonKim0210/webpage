package com.webpage.domain.view.service.impl;

import com.webpage.domain.view.dto.*;
import com.webpage.domain.view.entity.*;
import com.webpage.domain.view.repository.*;
import com.webpage.domain.view.vo.HeaderVO;
import com.webpage.domain.view.service.ViewService;
import com.webpage.domain.view.vo.ViewVO;
import com.webpage.global.utils.EmptyCheck;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ViewServiceImpl implements ViewService {

    @Value("#{${notice}}")
    Map<String, Integer> noticeInfo;

    private CompanyInfoRepository companyInfoRepository;
    private MenuListRepository menuListRepository;
    private TabListRepository tabListRepository;
    private ImageListRepository imageListRepository;
    private BoardListRepository boardListRepository;
    private DetailImageListRepository detailImageListRepository;

    @Override
    public void setCommonLayer(Model model, boolean isHeader, boolean isFooter) {
        if (isHeader) setHeader(model);
        if (isFooter) setFooter(model);
    }

    @Override
    public void setHeader(Model model) {
        CompanyInfo company = companyInfoRepository.findCompanyMainPhoneById(1);
        HeaderVO headerVO = new HeaderVO();
        headerVO.setMenuList(menuListRepository.findAllByNativeQueryOnd());
        headerVO.setCompanyMainPhone(company.convertToDTO().getCompanyMainPhone());
        model.addAttribute("header", headerVO);
    }

    @Override
    public void setFooter(Model model) {
        CompanyInfo company = companyInfoRepository.findAllById(1);
        FooterDTO footerDTO = company.convertToDTO();
        model.addAttribute("footer", footerDTO);
    }

    @Override
    public void setNoticeList(Model model) {
        setCommonLayer(model, true, true);
//        noticeInfo;   // 공지사항 목록 표시(메인화면에 표시할 항목임 / 나중에 menuId, tabId 확정되면 properties 파일 수정해야됨
    }

    @Override
    public String setViewPage(Model model, int menuId, int tabId, Pageable pageable) {
        setCommonLayer(model, true, true);

        List<TabListEntity> entityList = tabListRepository.findAllByMenuIdOrderByTabOrder(menuId);
        TabListEntity entity = tabListRepository.findByIdAndMenuId(tabId, menuId);
        StringBuilder templatePath = new StringBuilder("template/");
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

        MenuListEntity menuListEntity = menuListRepository.findAllById(menuId);

        model.addAttribute("menuId", menuId);
        model.addAttribute("tabId", tabId);
        model.addAttribute("menuInfo", menuListEntity.convertToMenuListDTO());
        model.addAttribute("tabList", viewVOList);
        model.addAttribute("tabData", getTabData(menuId, tabId, entity.convertToTabListDTO().getTemplateType(), pageable));
        return templatePath.toString();
    }

    private Object getTabData(int menuId, int tabId, int templateType, Pageable pageable) {
        Page<?> mainList = null;
        if (templateType == 1) {
            List<ImageListEntity> entities = imageListRepository.findAllByMenuIdAndTabId(menuId, tabId, pageable);
            List<ImageListDTO> images = new ArrayList<>();
            List<List<ImageListDTO>> mainImageList = new ArrayList<>();
            for (int i = 0; i < entities.size(); i++) {
                images.add(entities.get(i).convertToImageListDTO());
                if ((i + 1) % 4 == 0) {
                    mainImageList.add(images);
                    images = new ArrayList<>();
                }

                if (i + 1 == entities.size()) {
                    mainImageList.add(images);
                }
            }

            EmptyCheck.removeEmptyList(mainImageList);
            long totalCnt = imageListRepository.countAllByMenuIdAndTabId(menuId, tabId);
            mainList = new PageImpl(mainImageList, pageable, totalCnt);
        } else if (templateType == 2) {
            Sort sort = Sort.by(Sort.Direction.DESC, "writeDate");
            Pageable boardPageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
            Page<BoardListEntity> boardPageList = boardListRepository.findAllByMenuIdAndTabId(menuId, tabId, boardPageable);
            mainList = boardPageList.map(e -> e.convertToBoardListDTO());
        }

        return mainList;
    }

    @Override
    public void setViewImage(Model model, long id) {
        List<DetailImageListEntity> entity = detailImageListRepository.findAllByImageId(id);
        ImageListEntity imageListEntity = imageListRepository.findAllById(id);
        MenuListEntity menuListEntity = menuListRepository.findAllById(imageListEntity.convertToImageListDTO().getMenuId());
        model.addAttribute("menuInfo", menuListEntity.convertToMenuListDTO());
        model.addAttribute("imageInfo", entity.stream().map(e -> e.convertToDetailImageListDTO()).collect(Collectors.toList()));
    }

    @Override
    public void setViewBoard(Model model, long id) {
        BoardListEntity entity = boardListRepository.findAllById(id);
        BoardListDTO boardListDTO = entity.convertToBoardListDTO();
        MenuListEntity menuListEntity = menuListRepository.findAllById(boardListDTO.getMenuId());
        model.addAttribute("menuInfo", menuListEntity.convertToMenuListDTO());
        model.addAttribute("boardInfo", boardListDTO);
    }

    @Override
    public void setInsertPage(Model model, int menuId, int tabId, boolean isImage) {
        MenuListEntity menuListEntity = menuListRepository.findAllById(menuId);
        List<TabListEntity> tabListEntityList = tabListRepository.findAllByMenuId(menuId);
        model.addAttribute("menuId", menuId);
        model.addAttribute("tabId", tabId);
        model.addAttribute("menuInfo", menuListEntity.convertToMenuListDTO());
        model.addAttribute("tabInfo", tabListEntityList.stream().map(e -> e.convertToTabListDTO()).collect(Collectors.toList()));
    }
}
