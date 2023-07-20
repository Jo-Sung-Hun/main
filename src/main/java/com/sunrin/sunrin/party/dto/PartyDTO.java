package com.sunrin.sunrin.party.dto;

import com.sunrin.sunrin.party.domain.PartyEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartyDTO {
    private String partyName;
    private String partyPassword;
    private Integer batingNum;

    public PartyEntity toEntity() {
        return PartyEntity.builder()
                .partyName(partyName)
                .partyPassword(partyPassword)
                .batingNum(batingNum)
                .build();
    }
}
