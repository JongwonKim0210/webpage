package com.webpage.domain.view.controller;

import com.webpage.domain.view.service.ViewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ViewController {

    private ViewService viewService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        viewService.setCommonLayer(model, true, true);
        viewService.setNoticeList(model);
        return "Index";
    }

    @GetMapping("/details/{menuId}/{tabId}")
    public String details(Model model, @PathVariable("menuId") int menuId, @PathVariable("tabId") int tabId, Pageable pageable) {
        viewService.setCommonLayer(model, true, true);
        return viewService.setViewPage(model, menuId, tabId, pageable);
    }

    @GetMapping("/view/image/{id}")
    public String viewImage(Model model, @PathVariable("id") long id) {
        viewService.setCommonLayer(model, true, true);
        viewService.setViewImage(model, id);
        return "template/ImageViewerTemplate";
    }

    @GetMapping("/view/board/{id}")
    public String viewBoard(Model model, @PathVariable("id") long id) {
        viewService.setCommonLayer(model, true, true);
        viewService.setViewBoard(model, id);
        return "template/BoardViewerTemplate";
    }

    @GetMapping("/image/new/{menuId}/{tabId}")
    public String imageInsert(Model model, @PathVariable("menuId") int menuId, @PathVariable("tabId") int tabId) {
        viewService.setCommonLayer(model, true, true);
        viewService.setInsertPage(model, menuId, tabId, true);
        return "template/ImageInsertTemplate";
    }

    @GetMapping("/board/new/{menuId}/{tabId}")
    public String boardInsert(Model model, @PathVariable("menuId") int menuId, @PathVariable("tabId") int tabId) {
        viewService.setCommonLayer(model, true, true);
        viewService.setInsertPage(model, menuId, tabId, false);
        return "template/BoardInsertTemplate";
    }
}
