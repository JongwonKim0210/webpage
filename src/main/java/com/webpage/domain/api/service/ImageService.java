package com.webpage.domain.api.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ImageService {
    void getImageData(HttpServletRequest request, HttpServletResponse response);
}
