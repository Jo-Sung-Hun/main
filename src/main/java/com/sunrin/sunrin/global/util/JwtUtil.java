package com.sunrin.sunrin.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrin.sunrin.global.auth.dto.Result;
import com.sunrin.sunrin.global.auth.dto.UserLoginEntityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.secret}")
    private String secret; // JWT 서명에 사용할 비밀 키
    @Value("${jwt.refresh_token.expiration}")
    private Long refreshTokenExpiration; // 리프레시 토큰 만료 시간 (30일)
    @Value("${jwt.access_token.expiration}")
    private Long accessTokenExpiration; // 액세스 토큰 만료 시간 (1시간)
    private final ObjectMapper objectMapper;
    @Autowired
    public JwtUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    // new Date(LocalDateTime.now().getNano() + refreshTokenExpiration)
    @Async
    public String refreshTokenGenerateToken(UserLoginEntityUserDetails username, HttpServletRequest httpServletRequest) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpiration);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        Date date2 = Date.from(localDateTime.plusMonths(1).atZone(java.time.ZoneId.systemDefault()).toInstant());
        logger.info(String.valueOf(date2.getTime()));
            return Jwts.builder()
                    .setSubject(username.getUsername())
                    .setIssuedAt(date)
                    .setAudience(httpServletRequest.getRemoteAddr())
                    .setExpiration(date2)
                    .setNotBefore(new Date(date.getTime() + 1000))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
    }
    @Async
    public String accessTokenGenerateToken(UserLoginEntityUserDetails username,  HttpServletRequest httpServletRequest) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpiration);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        Date date2 = Date.from(localDateTime.plusMinutes(30).atZone(java.time.ZoneId.systemDefault()).toInstant());
        logger.info(String.valueOf(date2.getTime()));
        return Jwts.builder()
                .setSubject(username.getUsername())
                .setIssuedAt(date)
                        .setAudience(httpServletRequest.getRemoteAddr())
                .setNotBefore(new Date(date.getTime() + 1000))
                        .setExpiration(date2)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public void responseError(ServletResponse response, Integer code, String errorMsg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // encode 이슈 있음
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        Result jsonResult = new Result(code, errorMsg);
        jsonResult.setSuccess(false);
        OutputStream os = null;
        try {
            os = httpServletResponse.getOutputStream();
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setStatus(code);
            os.write(objectMapper.writeValueAsString(jsonResult).getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsernameFromToken(String jws) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jws)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
