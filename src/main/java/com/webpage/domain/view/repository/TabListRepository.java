package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.TabListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TabListRepository extends JpaRepository<TabListEntity, Integer> {

    List<TabListEntity> findAllByMenuIdAndUseTabNotOrderByTabOrder(int menuId, String useTab);
    List<TabListEntity> findAllByMenuIdAndUseTabNot(int menuId, String useTab);
    TabListEntity findByIdAndMenuIdAndUseTabNot(int id, int menuId, String useTab);

}
