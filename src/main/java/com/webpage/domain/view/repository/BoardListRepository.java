package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.BoardListEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListRepository extends JpaRepository<BoardListEntity, Long> {

    Page<BoardListEntity> findAllByMenuIdAndTabId(int menuId, int tabId, Pageable pageable);
    BoardListEntity findAllById(long id);

}
