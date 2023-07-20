package com.sunrin.sunrin.global.auth.dao;

import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserLoginEntity, UUID>{
    Optional<UserLoginEntity> findByUserLoginUsername(String userLoginUsername);
}
