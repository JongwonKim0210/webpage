package com.webpage.domain.api.service.impl;

import com.webpage.domain.api.service.RestApiService;
import com.webpage.domain.view.dto.BoardListDTO;
import com.webpage.domain.view.dto.DetailImageListDTO;
import com.webpage.domain.view.dto.ImageListDTO;
import com.webpage.domain.view.entity.ImageListEntity;
import com.webpage.domain.view.repository.BoardListRepository;
import com.webpage.domain.view.repository.DetailImageListRepository;
import com.webpage.domain.view.repository.ImageListRepository;
import com.webpage.global.utils.HashUtils;
import com.webpage.global.utils.UploadFileSave;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class RestApiServiceImpl implements RestApiService {

    @Value("${imagePath}")
    private String imageUploadPath;

    private ImageListRepository imageListRepository;
    private BoardListRepository boardListRepository;
    private DetailImageListRepository detailImageListRepository;

    @Autowired
    public RestApiServiceImpl(ImageListRepository imageListRepository, BoardListRepository boardListRepository, DetailImageListRepository detailImageListRepository) {
        this.imageListRepository = imageListRepository;
        this.boardListRepository = boardListRepository;
        this.detailImageListRepository = detailImageListRepository;
    }

    @Override
    public ResponseEntity uploadImage(int menuId, int tabId, List<MultipartFile> uploadFileList) {
        boolean thumb = true;
        long imageId = 0;
        int saveCnt = 0;
        ImageListEntity imageListEntity;
        for (MultipartFile f : uploadFileList) {
            String path = UploadFileSave.uploadFile(f, imageUploadPath, menuId, tabId);
            ImageListDTO imageListDTO = ImageListDTO.builder().menuId(menuId).tabId(tabId)
                                        .mimeType(f.getContentType()).path(path)
                                        .name(FilenameUtils.getName(f.getOriginalFilename()))
                                        .size(f.getSize()).writeDate(new Date()).build();

            if (thumb && null != path) {
                imageListEntity = imageListRepository.save(imageListDTO.toEntity());
                imageId = imageListEntity.convertToImageListDTO().getId();
            }

            if (null != path && imageId != 0) {
                DetailImageListDTO detailImageListDTO = DetailImageListDTO.builder().writeDate(new Date())
                                                        .imageId(imageId).mimeType(f.getContentType())
                                                        .name(FilenameUtils.getName(f.getOriginalFilename()))
                                                        .path(path).size(f.getSize()).build();
                detailImageListRepository.save(detailImageListDTO.toEntity());
            }

            thumb = false;
            saveCnt++;
        }

        HttpStatus status = uploadFileList.size() == saveCnt ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        return new ResponseEntity(status);
    }

    @Override
    public ResponseEntity uploadBoard(BoardListDTO boardListDTO) {
        String password = HashUtils.SHA_3_512.getHashValue(boardListDTO.getPassword());
        BoardListDTO boardInfo = BoardListDTO.builder()
                .menuId(boardListDTO.getMenuId()).tabId(boardListDTO.getTabId())
                .title(boardListDTO.getTitle()).content(boardListDTO.getContent())
                .writer("운영자").writeDate(new Date())
                .password(password).build();

        boardListRepository.save(boardInfo.toEntity());
        return new ResponseEntity(HttpStatus.OK);
    }
}
