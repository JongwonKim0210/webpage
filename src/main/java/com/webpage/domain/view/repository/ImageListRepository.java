package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.ImageListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageListRepository extends JpaRepository<ImageListEntity, Long> {

    List<ImageListEntity> findAllByMenuIdAndTabId(int menuId, int tabId);
    ImageListEntity findAllById(long id);

}
