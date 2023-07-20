package com.sunrin.sunrin.global.auth.domain;

public class StopWatchTarget {
    private String username;
    public StopWatchTarget(String username) {
        this.username = username;
    }

    public StopWatchTarget() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
