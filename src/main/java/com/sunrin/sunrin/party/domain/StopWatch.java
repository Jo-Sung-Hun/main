package com.sunrin.sunrin.party.domain;

public class StopWatch {
    private String time;
    private String targetUserUsername;

    public StopWatch(String time, String targetUserUsername) {
        this.time = time;
        this.targetUserUsername = targetUserUsername;
    }

    public StopWatch() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTargetUserUsername() {
        return targetUserUsername;
    }

    public void setTargetUserUsername(String targetUserUsername) {
        this.targetUserUsername = targetUserUsername;
    }
}
