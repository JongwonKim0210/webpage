package com.webpage.domain.view.service;

import org.springframework.ui.Model;

import java.util.Map;

public interface ViewService {

    void setCommonLayer(Model model, boolean isHeader, boolean isFooter);
    void setHeader(Model model);
    void setFooter(Model model);
    void setNoticeList(Model model);

    String setViewPage(Model model, int menuId, int tabId);

}
