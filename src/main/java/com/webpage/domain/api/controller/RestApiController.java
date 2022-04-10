package com.webpage.domain.api.controller;

import com.webpage.domain.api.service.RestApiService;
import com.webpage.domain.view.dto.BoardListDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RestApiController {

    private RestApiService apiService;

    @PostMapping("/put/image")
    public ResponseEntity uploadImageData(@RequestParam("menuId") int menuId, @RequestParam("tabId") int tabId, @RequestParam("files") List<MultipartFile> uploadFileList) {
        return apiService.uploadImage(menuId, tabId, uploadFileList);
    }

    @PostMapping("/put/board")
    public ResponseEntity uploadBoardData(@RequestBody BoardListDTO boardListDTO) {
        return apiService.uploadBoard(boardListDTO);
    }
}
