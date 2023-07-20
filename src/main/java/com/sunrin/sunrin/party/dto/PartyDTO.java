package com.sunrin.sunrin.party.dto;

import com.sunrin.sunrin.party.domain.PartyEntity;
import lombok.*;

import java.util.Arrays;

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
    private Integer goal;
    private String[] exercise;
    public PartyEntity toEntity() {
        final String[] adder = {""};

        Arrays.stream(exercise).toList().stream().forEach(a -> {
            adder[0] += a + ":";
        });
        return PartyEntity.builder()
                .partyName(partyName)
                .partyPassword(partyPassword)
                .batingNum(batingNum)
                .userLoginEntities(adder[0])
                .goalScore(goal)
                .build();
    }
}
