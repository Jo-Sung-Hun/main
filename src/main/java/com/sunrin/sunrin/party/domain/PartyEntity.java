package com.sunrin.sunrin.party.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class PartyEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;
    private String partyName;
    private String partyPassword;
    private Integer batingNum;
    private String ownerUsername;
    private String userLoginEntities;
    private Integer goalScore;

}
