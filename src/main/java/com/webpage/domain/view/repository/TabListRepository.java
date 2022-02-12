package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.TabListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TabListRepository extends JpaRepository<TabListEntity, Integer> {

    List<TabListEntity> findAllByMenuIdOrderByTabOrder(Integer menuId);
    TabListEntity findByIdAndMenuId(Integer id, Integer menuId);

}
