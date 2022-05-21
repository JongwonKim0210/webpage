package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.MenuListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MenuListRepository extends JpaRepository<MenuListEntity, Integer> {

    MenuListEntity findAllById(int id);
    List<MenuListEntity> findAllByIdNot(int id);
    List<MenuListEntity> findAllByOrderByMenuOrder();

    @Query(value = "SELECT a.idx AS menuId, a.menuName, b.idx AS tabId FROM menulist a LEFT JOIN tablist b ON a.idx = b.menuId WHERE b.idx is not null GROUP BY a.idx", nativeQuery = true)
    List<Map<String, Object>> findAllByNativeQueryOne();
}
