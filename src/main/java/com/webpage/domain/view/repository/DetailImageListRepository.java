package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.DetailImageListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailImageListRepository extends JpaRepository<DetailImageListEntity, Integer> {
    List<DetailImageListEntity> findAllByImageId(long imageId);
    DetailImageListEntity findAllByIdAndImageId(int id, long imageId);
}
