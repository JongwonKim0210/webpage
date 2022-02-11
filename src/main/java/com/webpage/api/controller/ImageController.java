package com.webpage.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/image")
public class ImageController {

    @RequestMapping("/download")
    public void getImageData(HttpServletRequest request, HttpServletResponse response) throws Exception {

        File imageFile = new File("");
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(imageFile)); BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            if (imageFile.exists()) {
                String mimeType = "application/x-msdownload";
                response.setContentType(mimeType);
                this.setDisposition("", request, response);
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
        String browser = this.getBrowser(request);
        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;

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
