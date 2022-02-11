package com.webpage.api.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ImageService {
    void getImageData(HttpServletRequest request, HttpServletResponse response);
}
