package com.sunrin.sunrin.global.auth.api;

import com.sunrin.sunrin.global.auth.application.PrincipalDetailsService;
import com.sunrin.sunrin.global.auth.application.UserCRUDServiceImpl;
import com.sunrin.sunrin.global.auth.domain.UserLoginEntityUserDetails;
import com.sunrin.sunrin.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class JwtServiceController {
    private static final Logger logger = LoggerFactory.getLogger(JwtServiceController.class);
    private final JwtUtil jwtUtil;
    private final UserCRUDServiceImpl userAuthService;
    private final PrincipalDetailsService principalDetailsService;
    @Autowired
    public JwtServiceController(JwtUtil jwtUtil, UserCRUDServiceImpl userAuthService, PrincipalDetailsService principalDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userAuthService = userAuthService;

        this.principalDetailsService = principalDetailsService;
    }
    @RequestMapping(value = "/api/v1/test", method = RequestMethod.GET)
    public Object test(HttpServletRequest httpServletRequest){
        String jwt = String.valueOf(httpServletRequest.getHeader("Authorization").startsWith("Bearer"));
        logger.info(jwt);
        return new ResponseEntity<>("good", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/jwt/refresh/token", method = RequestMethod.POST)
    public Object refreshToken(HttpServletRequest httpServletRequest){
        String headerRefresh = httpServletRequest.getHeader("Authorization-Refresh");
        String headerRequest = httpServletRequest.getHeader("Authorization-Reqeust");
        if (headerRefresh == null || headerRequest == null){
            return new ResponseEntity<>("bad", HttpStatus.BAD_REQUEST);
        }
        if (!(Objects.equals(jwtUtil.getUsernameFromToken(headerRefresh), jwtUtil.getUsernameFromToken(headerRequest)))){
            return new ResponseEntity<>("bad", HttpStatus.BAD_REQUEST);
        }

        UserLoginEntityUserDetails user = (UserLoginEntityUserDetails) principalDetailsService.loadUserByUsername(jwtUtil.getUsernameFromToken(headerRefresh));
        logger.info("headerRefresh {}", headerRefresh);
        logger.info("headerRequest {}", headerRequest);
        //jwtUtil.refreshTokenGenerateToken(headerRefresh, headerRequest);
        return new ResponseEntity<>(jwtUtil.refreshTokenGenerateToken(user, null, httpServletRequest), HttpStatus.OK);
    }

}
