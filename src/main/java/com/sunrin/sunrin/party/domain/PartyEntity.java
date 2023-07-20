package com.sunrin.sunrin.party.domain;

import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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
    @OneToOne
    private UserLoginEntity ownerUserLoginEntity;
    @OneToMany(fetch = jakarta.persistence.FetchType.LAZY)
    private List<UserLoginEntity> userLoginEntities;

}
