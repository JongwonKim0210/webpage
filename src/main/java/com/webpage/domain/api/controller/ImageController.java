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

    @RequestMapping("/download/{imageId}")
    public void getImageData(HttpServletRequest request, HttpServletResponse response, @PathVariable("imageId") long imageId) {
        imageService.getImageData(request, response, imageId);
    }

    @RequestMapping("/download/{imageId}/{detailId}")
    public void getImageData(HttpServletRequest request, HttpServletResponse response, @PathVariable("imageId") long imageId, @PathVariable("detailId") int detailId) {
        imageService.getImageData(request, response, imageId, detailId);
    }

}
