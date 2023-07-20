package com.sunrin.sunrin.party.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunrin.sunrin.party.domain.PartyEntity;
import com.sunrin.sunrin.party.dto.PartyDTO;
import com.sunrin.sunrin.party.dto.PartyJoinDTO;

import java.util.List;
import java.util.Optional;

public interface PartyCRUDService {
    Optional<PartyEntity> save(PartyEntity partyDTO);
    Optional<PartyEntity> update(PartyEntity partyDTO);

    void delete(PartyDTO partyDTO);
    Boolean isExistParty(PartyDTO partyDTO);
    List<PartyEntity> findByOwnerUsername(String ownerUsername);

    Boolean addUserToParty(PartyJoinDTO partyDTO, String addTargetUsername) throws JsonProcessingException;

    List<PartyEntity> findAll();
    PartyEntity deleteByUuid(String uuid);
}
