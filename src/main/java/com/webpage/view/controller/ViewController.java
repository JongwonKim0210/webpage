package com.webpage.view.controller;

import com.webpage.utils.CommonView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController extends CommonView {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
//        this.setCommonLayer(model, true, true);
        return "Index";
    }

}
