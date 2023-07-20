package com.sunrin.sunrin.global.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResult {
    private String accessToken;

    @Override
    public String toString() {
        return "JWTResult{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
