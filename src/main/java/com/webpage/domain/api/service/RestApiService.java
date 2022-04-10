package com.webpage.domain.api.service;

import com.webpage.domain.view.dto.BoardListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestApiService {

    ResponseEntity uploadImage(int menuId, int tabId, List<MultipartFile> uploadFileList);
    ResponseEntity uploadBoard(BoardListDTO boardListDTO);

}
