package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.TabListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TabListRepository extends JpaRepository<TabListEntity, Integer> {

    List<TabListEntity> findAllByMenuIdOrderByTabOrder(int menuId);
    List<TabListEntity> findAllByMenuId(int menuId);
    TabListEntity findByIdAndMenuId(int id, int menuId);

}
