package com.sunrin.sunrin.global.auth.dto;

public class AreadyUsernameResponse {
    private String message;

    public AreadyUsernameResponse(String message) {
        this.message = message;
    }

    public AreadyUsernameResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
