package com.sunrin.sunrin.party.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JsonExceptionErrorResponse {
    private String simpleMessage;
    private String message;
}
