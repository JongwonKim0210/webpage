package com.webpage.domain.api.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface ImageService {
    void getImageData(HttpServletRequest request, HttpServletResponse response, long imageId);
    void getImageData(HttpServletRequest request, HttpServletResponse response, long imageId, int imageDetailId);
}
