package com.sunrin.sunrin.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrin.sunrin.global.auth.dao.UserRepository;
import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import com.sunrin.sunrin.global.auth.dto.Result;
import com.sunrin.sunrin.global.auth.domain.UserLoginEntityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.secret}")
    private String secret; // JWT 서명에 사용할 비밀 키
    @Value("${jwt.refresh_token.expiration}")
    private Long refreshTokenExpiration; // 리프레시 토큰 만료 시간 (30일)
    @Value("${jwt.access_token.expiration}")
    private Long accessTokenExpiration; // 액세스 토큰 만료 시간 (1시간)
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    @Autowired
    public JwtUtil(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }
    // new Date(LocalDateTime.now().getNano() + refreshTokenExpiration)
    @Async
    public String refreshTokenGenerateToken(UserLoginEntityUserDetails username, HttpServletRequest httpServletRequest) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpiration);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        Claims claims = Jwts.claims();
        claims.put("auth", "ROLE_USER");
        claims.setSubject(username.getUsername());
        logger.info("claims get sub {}", claims.getSubject());
        Date date2 = Date.from(localDateTime.plusMonths(1).atZone(java.time.ZoneId.systemDefault()).toInstant());
        logger.info(String.valueOf(date2.getTime()));
            return Jwts.builder()
                    .setSubject(username.getUsername())
                    .setIssuedAt(date)
                    .setAudience(httpServletRequest.getRemoteAddr())
                    .setExpiration(date2)
                    .setClaims(claims)
                    .setNotBefore(new Date(date.getTime() + 1000))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
    }
    public Authentication getAuthentication(String accessToken) {
        //토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        logger.info("claims get sub {}", claims.getSubject());
        Optional<UserLoginEntity> userLoginEntity = userRepository.findByUserLoginUsername(claims.getSubject());
        UserLoginEntityUserDetails principal = new UserLoginEntityUserDetails(userLoginEntity.get(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    @Async
    public String accessTokenGenerateToken(UserLoginEntityUserDetails username,  HttpServletRequest httpServletRequest) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpiration);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        Date date2 = Date.from(localDateTime.plusMinutes(30).atZone(java.time.ZoneId.systemDefault()).toInstant());
        logger.info(String.valueOf(date2.getTime()));
        Claims claims = Jwts.claims();
        claims.put("auth", "ROLE_USER");
        claims.setSubject(username.getUsername());
        logger.info("claims get sub {}", claims.getSubject());
        return Jwts.builder()
                .setSubject(username.getUsername())
                .setIssuedAt(date)
                .setClaims(claims)
                        .setAudience(httpServletRequest.getRemoteAddr())
                .setNotBefore(new Date(date.getTime() + 1000))
                        .setExpiration(date2)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    // 리프레쉬 토큰 재발급 로직
    @Async
    public String refreshTokenGenerateToken(UserLoginEntityUserDetails username, String refreshToken, HttpServletRequest httpServletRequest) {
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
