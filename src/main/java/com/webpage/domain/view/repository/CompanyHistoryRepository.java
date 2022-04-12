package com.webpage.domain.view.repository;

import com.webpage.domain.view.entity.CompanyHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyHistoryRepository extends JpaRepository<CompanyHistoryEntity, Integer> {

    List<CompanyHistoryEntity> findAll();

}
