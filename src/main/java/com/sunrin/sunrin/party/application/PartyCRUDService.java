package com.sunrin.sunrin.party.application;

import com.sunrin.sunrin.party.domain.PartyEntity;
import com.sunrin.sunrin.party.dto.PartyDTO;

import java.util.List;
import java.util.Optional;

public interface PartyCRUDService {
    Optional<PartyEntity> save(PartyDTO partyDTO);
    Optional<PartyEntity> update(PartyDTO partyDTO);
    void delete(PartyDTO partyDTO);
    Boolean isExistParty(PartyDTO partyDTO);
    List<PartyEntity> findByOwnerUsername(String ownerUsername);
    Boolean addUserToParty(PartyDTO partyDTO, String username);

}
