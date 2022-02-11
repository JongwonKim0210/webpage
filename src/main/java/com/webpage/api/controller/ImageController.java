package com.webpage.api.controller;

import com.webpage.api.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private ImageService imageService;

    @RequestMapping("/download")
    public void getImageData(HttpServletRequest request, HttpServletResponse response) {
        imageService.getImageData(request, response);
    }
}
