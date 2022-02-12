package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.MenuListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuListRepository extends JpaRepository<MenuListEntity, Integer> {
    List<MenuListEntity> findAllByOrderByMenuOrder();
}
