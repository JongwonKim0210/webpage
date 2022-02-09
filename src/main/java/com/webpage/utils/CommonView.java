package com.webpage.utils;

import org.springframework.ui.Model;

public class CommonView {

    public void setCommonLayer(Model model, boolean isHeader, boolean isFooter) {
        if (isHeader) setHeader(model);
        if (isFooter) setFooter(model);
    }

    public void setHeader(Model model) {

    }

    public void setFooter(Model model) {

    }

}
