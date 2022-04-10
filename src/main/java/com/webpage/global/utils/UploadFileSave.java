package com.webpage.global.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadFileSave {

    public static String uploadFile(MultipartFile file, Object ... pathInfo) {
        String fullSavePath;
        StringBuilder uploadPath = new StringBuilder();
        for (Object o : pathInfo) {
            uploadPath.append(o).append(File.separator);
        }

        Path path = Paths.get(uploadPath.toString());
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (InputStream inputStream = file.getInputStream()) {
            String fileName = FilenameUtils.getName(file.getOriginalFilename());
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            fullSavePath = filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return fullSavePath;
    }

}
