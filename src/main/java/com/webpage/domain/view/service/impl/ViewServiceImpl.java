package com.webpage.domain.view.service.impl;

import com.webpage.domain.view.dto.*;
import com.webpage.domain.view.entity.*;
import com.webpage.domain.view.repository.*;
import com.webpage.domain.view.vo.HeaderVO;
import com.webpage.domain.view.service.ViewService;
import com.webpage.domain.view.vo.ViewVO;
import com.webpage.global.utils.CommonUtils;
import com.webpage.global.utils.EmptyCheck;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

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
    private CompanyHistoryRepository companyHistoryRepository;

    @Override
    public void setCommonLayer(Model model, boolean isHeader, boolean isFooter) {
        if (isHeader) setHeader(model);
        if (isFooter) setFooter(model);
    }

    @Override
    public void setHeader(Model model) {
        CompanyInfo company = companyInfoRepository.findCompanyMainPhoneById(1);
        HeaderVO headerVO = new HeaderVO();
        headerVO.setMenuList(menuListRepository.findAllByNativeQueryOne());
        headerVO.setCompanyMainPhone(company.convertToDTO().getCompanyMainPhone());
        model.addAttribute("header", headerVO);
    }

    @Override
    public void setFooter(Model model) {
        CompanyInfo company = companyInfoRepository.findAllById(1);
        FooterDTO footerDTO = company.convertToDTO();
        List<TabListEntity> entityList = tabListRepository.findAllByMenuIdAndUseTabNotOrderByTabOrder(1, "N");
        List<ViewVO> viewVOList = new ArrayList<>();
        for(TabListEntity tabListEntity : entityList) {
            TabListDTO tabListDTO = tabListEntity.convertToTabListDTO();
            viewVOList.add(ViewVO.builder().menuId(tabListDTO.getMenuId()).tabId(tabListDTO.getId()).tabName(tabListDTO.getTabName()).templateType(tabListDTO.getTemplateType()).build());
        }

        footerDTO.setCompanyViewList(viewVOList);

        List<MenuListEntity> menuListEntityList = menuListRepository.findAllByIdNot(1);
        footerDTO.setMenuViewList(menuListEntityList.stream().map(MenuListEntity::convertToMenuListDTO).toList());
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

        List<TabListEntity> entityList = tabListRepository.findAllByMenuIdAndUseTabNotOrderByTabOrder(menuId, "N");
        TabListEntity entity = tabListRepository.findByIdAndMenuIdAndUseTabNot(tabId, menuId, "N");
        StringBuilder templatePath = new StringBuilder("template/");
        switch (entity.convertToTabListDTO().getTemplateType()) {
            case 0 -> templatePath.append("CompanyInfoTemplate");
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
        if (templateType == 1) {
            List<ImageListEntity> entities = imageListRepository.findAllByMenuIdAndTabId(menuId, tabId, pageable);
            List<List<ImageListDTO>> mainImageList = CommonUtils.sortImageList(entities, ImageListDTO.class);
            EmptyCheck.removeEmptyList(mainImageList);
            long totalCnt = imageListRepository.countAllByMenuIdAndTabId(menuId, tabId);
            return new PageImpl(mainImageList, pageable, totalCnt);
        } else if (templateType == 2) {
            Sort sort = Sort.by(Sort.Direction.DESC, "writeDate");
            Pageable boardPageable = PageRequest.of(pageable.getPageNumber(), 10, sort);
            Page<BoardListEntity> boardPageList = boardListRepository.findAllByMenuIdAndTabId(menuId, tabId, boardPageable);
            return boardPageList.map(BoardListEntity::convertToBoardListDTO);
        } else {    // 회사정보구역 탭 아이디(고정값)에 따라 분기처리되어 값을 던져주어야 함
            Object object = null;
            switch (tabId) {
                case 2 :
                    List<CompanyHistoryEntity> companyHistoryEntityList = companyHistoryRepository.findAll();
                    object = companyHistoryEntityList.stream().map(CompanyHistoryEntity::convertToCompanyHistoryDTO).toList();
                    break;
                case 3 :
                    ImageListEntity organizationInfo = imageListRepository.findAllByMenuIdAndTabIdOrderByMenuId(menuId, tabId);
                    object = organizationInfo.convertToImageListDTO();
                    break;
                case 4 :
                    List<ImageListEntity> certificateList = imageListRepository.findAllByMenuIdAndTabId(menuId, tabId);
                    object = CommonUtils.sortImageList(certificateList, ImageListDTO.class);
                    break;
                case 5 :
                    CompanyInfo companyInfo = companyInfoRepository.findAllById(1);
                    object =  companyInfo.convertToDTO();
                    break;
            }
            return object;
        }
    }

    @Override
    public void setViewImage(Model model, long id) {
        List<DetailImageListEntity> entity = detailImageListRepository.findAllByImageId(id);
        ImageListEntity imageListEntity = imageListRepository.findAllById(id);
        MenuListEntity menuListEntity = menuListRepository.findAllById(imageListEntity.convertToImageListDTO().getMenuId());
        model.addAttribute("menuInfo", menuListEntity.convertToMenuListDTO());
        model.addAttribute("imageInfo", entity.stream().map(DetailImageListEntity::convertToDetailImageListDTO).toList());
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
        List<TabListEntity> tabListEntityList = tabListRepository.findAllByMenuIdAndUseTabNot(menuId, "N");
        model.addAttribute("menuId", menuId);
        model.addAttribute("tabId", tabId);
        model.addAttribute("menuInfo", menuListEntity.convertToMenuListDTO());
        model.addAttribute("tabInfo", tabListEntityList.stream().map(TabListEntity::convertToTabListDTO).toList());
    }
}
