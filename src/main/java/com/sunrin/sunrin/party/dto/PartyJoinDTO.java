package com.sunrin.sunrin.party.dto;

public class PartyJoinDTO {

    private String joinTargetPartyUUID;
    public PartyJoinDTO(String joinTargetPartyUUID) {
        this.joinTargetPartyUUID = joinTargetPartyUUID;
    }

    public PartyJoinDTO() {
    }

    public String getJoinTargetPartyUUID() {
        return joinTargetPartyUUID;
    }

    public void setJoinTargetPartyUUID(String joinTargetPartyUUID) {
        this.joinTargetPartyUUID = joinTargetPartyUUID;
    }

    @Override
    public String toString() {
        return "PartyJoinDTO{" +
                "joinTargetPartyUUID='" + joinTargetPartyUUID + '\'' +
                '}';
    }
}
