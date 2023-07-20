package com.sunrin.sunrin.global.auth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import com.sunrin.sunrin.global.auth.dto.UserLoginData;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;


public interface UserAuthService {
    String login(UserLoginData userLoginData, HttpServletRequest httpServletRequest) throws JsonProcessingException;
    Optional<UserLoginEntity> signin(UserLoginData userLoginData);

}