package com.sunrin.sunrin.global.auth.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sunrin.sunrin.global.auth.application.UserAuthServiceImpl;
import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import com.sunrin.sunrin.global.auth.dto.AreadyUsernameResponse;
import com.sunrin.sunrin.global.auth.dto.UserLoginData;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "유저 인증을 위해 만든 API 입니다", description = "게시물 API")
public class UserAuthController {
    private final UserAuthServiceImpl userAuthService;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);
    @Autowired
    public UserAuthController(UserAuthServiceImpl userAuthService, ObjectMapper objectMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAuthService = userAuthService;
        this.objectMapper = objectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Parameters({
            @Parameter(name = "userLoginEmail", description = "유저 로그인을 위한 Username(Email)", required = true),
            @Parameter(name = "userLoginPassword", description = "유저 로그인을 위한 Password", required = true),

    })
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "요청 데이터 문제로 에러"),
    })
    @RequestMapping(value = "/api/v1/login", method = RequestMethod.POST)
    public Object userMainLoginAuth(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        String loginResult = userAuthService.login(objectMapper.readValue(json, UserLoginData.class), httpServletRequest);
        String userResultToString = loginResult.toString();//ㅁ0냐ㅐ엄ㄴ어    마재ㅑㅇ
        logger.info("loginResult {}", userResultToString);
        return new ResponseEntity<>(loginResult, HttpStatus.ACCEPTED);
    }
    // 유저 로그인 개발시 잠시 기능 통합을 위해 같은 Class 안에 넣어놨음.
    // 유저 회원가입시 이미 존재하는 아이디입니다 따로 handling
    @RequestMapping(value = "/api/v1/signup", method = RequestMethod.POST)
    public Object userMainSigninAuth(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        /**
         *  {
         *   "message": "이미 존재하는 아이디입니다."
         *   }
         */
        UserLoginData userLoginData = objectMapper.readValue(json, UserLoginData.class);
        logger.info("userLoginData {}", userLoginData.toString());
        userLoginData.setUserLoginPassword(bCryptPasswordEncoder.encode(userLoginData.getUserLoginPassword()));
        Optional<UserLoginEntity> loginResult;
        try {
            loginResult = userAuthService.signin(userLoginData);
        } catch (Exception e) {
            return new AreadyUsernameResponse("이미 존재하는 아이디입니다.");
        }
        String userResultToString = loginResult.toString();
        logger.info("loginResult {}", userResultToString);
        return new ResponseEntity<>(objectMapper.writeValueAsString(loginResult), HttpStatus.ACCEPTED);
    }
}