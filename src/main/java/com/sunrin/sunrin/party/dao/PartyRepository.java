package com.sunrin.sunrin.party.dao;

import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import com.sunrin.sunrin.party.domain.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PartyRepository extends JpaRepository<PartyEntity, UUID> {
    Boolean existsByPartyName(String partyName);
    List<PartyEntity> findAllByOwnerUserLoginEntity(UserLoginEntity userLoginEntity);
    PartyEntity findByPartyName(String partyName);
}