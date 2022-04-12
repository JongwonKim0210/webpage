package com.webpage.global.utils;

import com.webpage.domain.view.entity.ImageListEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static <T> List<List<T>> sortImageList(List<ImageListEntity> list, Class<T> type) {
        List<List<T>> convertList = new ArrayList<>();
        List<T> images = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            images.add((T) list.get(i).convertToImageListDTO());
            if ((i + 1) % 4 == 0) {
                convertList.add(images);
                images = new ArrayList<>();
            }

            if (i + 1 == list.size()) {
                convertList.add(images);
            }
        }

        return convertList;
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
