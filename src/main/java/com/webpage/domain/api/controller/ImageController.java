package com.webpage.domain.api.controller;

import com.webpage.domain.api.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private ImageService imageService;

    @RequestMapping("/download/{menuId}/{tabId}/{imageId}")
    public void getImageData(HttpServletRequest request, HttpServletResponse response, @PathVariable("menuId") int menuId, @PathVariable("tabId") int tabId, @PathVariable("imageId") Long imageId) {
        imageService.getImageData(request, response, menuId, tabId, imageId);
    }
}
