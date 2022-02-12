package com.webpage.domain.view.controller;

import com.webpage.domain.view.service.ViewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String details(Model model, @PathVariable("menuId") int menuId, @PathVariable("tabId") int tabId) {
        return viewService.setViewPage(model, menuId, tabId);
    }

}
