package com.sunrin.sunrin.global.auth.domain;


import com.sunrin.sunrin.global.auth.dto.UserLoginData;
import com.sunrin.sunrin.party.domain.PartyEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class UserLoginEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;
    @Column(unique = true, nullable = false, length = 50)
    private String userLoginUsername;
    @Column(nullable = false, length = 9999999)
    private String userLoginPassword;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PartyEntity> partyEntities;
    private String stopwatchLocalDateTime;
    private String role;
    @Builder
    public UserLoginEntity(String userLoginUsername, String userLoginPassword) {
        this.role = "ROLE_USER";
        this.userLoginUsername = userLoginUsername;
        this.userLoginPassword = userLoginPassword;
    }

    @Builder
    public UserLoginEntity(UserLoginData accountReqDto) {
        this.role = "ROLE_USER";
        this.userLoginUsername = accountReqDto.getUserLoginEmail();
        this.userLoginPassword = accountReqDto.getUserLoginPassword();
    }

    public UserLoginEntity() {
    }

    public String getUserLoginUsername() {
        return userLoginUsername;
    }

    public String getStopwatchLocalDateTime() {
        return stopwatchLocalDateTime;
    }

    public void setStopwatchLocalDateTime(String stopwatchLocalDateTime) {
        this.stopwatchLocalDateTime = stopwatchLocalDateTime;
    }

    public void setUserLoginUsername(String userLoginUsername) {
        this.userLoginUsername = userLoginUsername;
    }

    public List<PartyEntity> getPartyEntities() {
        return partyEntities;
    }

    public void setPartyEntities(List<PartyEntity> partyEntities) {
        this.partyEntities = partyEntities;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserLoginEmail() {
        return userLoginUsername;
    }

    public void setUserLoginEmail(String userLoginEmail) {
        this.userLoginUsername = userLoginEmail;
    }

    public String getUserLoginPassword() {
        return userLoginPassword;
    }

    public void setUserLoginPassword(String userLoginPassword) {
        this.userLoginPassword = userLoginPassword;
    }

    @Override
    public String toString() {
        return "UserLoginEntity{" +
                "uuid=" + uuid +
                ", userLoginEmail='" + userLoginUsername + '\'' +
                ", userLoginPassword='" + userLoginPassword + '\'' +
                '}';
    }
}
