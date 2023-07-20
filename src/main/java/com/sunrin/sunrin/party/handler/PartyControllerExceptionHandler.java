package com.sunrin.sunrin.party.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunrin.sunrin.party.dto.JsonExceptionErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PartyControllerExceptionHandler {
    @ExceptionHandler(JsonProcessingException.class)
    public String handleException(Exception e) {
        JsonExceptionErrorResponse jsonExceptionErrorResponse = JsonExceptionErrorResponse.builder()
                .simpleMessage(e.getMessage())
                .message("Json 파싱중 에러가 발생했습니다.")
                .build();
        return e.getMessage();
    }
}
