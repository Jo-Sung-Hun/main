package com.sunrin.sunrin.global.auth.dao;

import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;

import java.util.Optional;

public interface UserAuthRepository {
    Optional<UserLoginEntity> findByEmail(String email);
}
