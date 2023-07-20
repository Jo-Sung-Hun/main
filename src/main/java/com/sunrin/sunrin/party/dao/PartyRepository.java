package com.sunrin.sunrin.party.dao;

import com.sunrin.sunrin.party.domain.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PartyRepository extends JpaRepository<PartyEntity, UUID> {
    Boolean existsByPartyName(String partyName);
    List<PartyEntity> findAllByOwnerUsername(String userLoginEntity);
    PartyEntity findByPartyName(String partyName);
    PartyEntity findByUuid(UUID uuid);
}