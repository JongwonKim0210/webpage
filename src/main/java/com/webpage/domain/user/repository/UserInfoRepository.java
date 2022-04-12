package com.webpage.domain.user.repository;

import com.webpage.domain.user.entity.UserinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserinfoEntity, String> {

    Optional<UserinfoEntity> findAllByIdAndPassword(String id, String password);

}
