package com.webpage.domain.api.service.impl;

import com.webpage.domain.api.service.ImageService;
import com.webpage.domain.view.entity.DetailImageListEntity;
import com.webpage.domain.view.entity.ImageListEntity;
import com.webpage.domain.view.repository.DetailImageListRepository;
import com.webpage.domain.view.repository.ImageListRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${imagePath}")
    private String imageUploadPath;

    private ImageListRepository imageList;
    private DetailImageListRepository detailList;

    @Autowired
    public ImageServiceImpl(ImageListRepository imageList, DetailImageListRepository detailList) {
        this.imageList = imageList;
        this.detailList = detailList;
    }

    @Override
    public void getImageData(HttpServletRequest request, HttpServletResponse response, long imageId) {
        ImageListEntity requestImageInfo = imageList.findAllById(imageId);
        setImageData(request, response, requestImageInfo.convertToImageListDTO().getPath());
    }

    @Override
    public void getImageData(HttpServletRequest request, HttpServletResponse response, long imageId, int imageDetailId) {
        DetailImageListEntity requestImageInfo = detailList.findAllByIdAndImageId(imageDetailId, imageId);
        setImageData(request, response, requestImageInfo.convertToDetailImageListDTO().getPath());
    }

    private void setImageData(HttpServletRequest request, HttpServletResponse response, String path) {
        File imageFile = new File(path);
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(imageFile));
             BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            if (imageFile.exists()) {
                String mimeType = "application/x-msdownload";
                response.setContentType(mimeType);
                setDisposition(FilenameUtils.getBaseName(imageFile.getName()), request, response);
                FileCopyUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } else {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.println("<html xml:lang=\"ko\">");
                writer.println("<head>");
                writer.println("<script type=\"text/javascript\">");
                writer.println("alert(Don't find image file);");
                writer.println("history.go(-1)");
                writer.println("</script>");
                writer.println("</head>");
                writer.println("<body></body>");
                writer.println("</html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDisposition(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String browser = getBrowser(request);
        String dispositionPrefix = "attachment; filename=";
        String encodedFilename;

        if (browser.equalsIgnoreCase("MSIE")) {
            encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        } else if (browser.equalsIgnoreCase("Trident")) {
            encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        } else if (browser.equalsIgnoreCase("Firefox")) {
            encodedFilename = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), "8859_1") + "\"";
        } else if (browser.equalsIgnoreCase("Opera")) {
            encodedFilename = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), "8859_1") + "\"";
        } else if (browser.equalsIgnoreCase("Chrome")) {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < fileName.length(); i++) {
                char c = fileName.charAt(i);
                if (c > '~') {
                    buffer.append(URLEncoder.encode("" + c, StandardCharsets.UTF_8));
                } else {
                    buffer.append(c);
                }
            }
            encodedFilename = buffer.toString();
        } else {
            throw new IOException("Not Supported browser");
        }

        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
    }

    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        String browserType = "Firefox";
        if (header.contains("MSIE")) {
            browserType = "MSIE";
        } else if (header.contains("Trident")) {
            browserType = "Trident";
        } else if (header.contains("Chrome")) {
            browserType = "Chrome";
        } else if (header.contains("Opera")) {
            browserType = "Opera";
        }

        return browserType;
    }
}
