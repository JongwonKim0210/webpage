package com.webpage.domain.view.service;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface ViewService {

    void setCommonLayer(Model model, boolean isHeader, boolean isFooter);
    void setHeader(Model model);
    void setFooter(Model model);
    void setNoticeList(Model model);

    String setViewPage(Model model, int menuId, int tabId, Pageable pageable);
    void setViewImage(Model model, long id);
    void setViewBoard(Model model, long id);
    void setInsertPage(Model model, int menuId, int tabId, boolean isImage);
}
