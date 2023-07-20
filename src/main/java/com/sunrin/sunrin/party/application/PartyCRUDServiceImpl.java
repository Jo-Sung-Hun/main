package com.sunrin.sunrin.party.application;

import com.sunrin.sunrin.global.auth.dao.UserRepository;
import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import com.sunrin.sunrin.party.dao.PartyRepository;
import com.sunrin.sunrin.party.domain.PartyEntity;
import com.sunrin.sunrin.party.dto.PartyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PartyCRUDServiceImpl implements PartyCRUDService{
    private static final Logger logger = LoggerFactory.getLogger(PartyCRUDServiceImpl.class);
    private final PartyRepository partyRepository;
    private final UserRepository userRepository;
    @Autowired
    public PartyCRUDServiceImpl(PartyRepository partyCRUDService, UserRepository userRepository) {
        this.partyRepository = partyCRUDService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<PartyEntity> save(PartyEntity partyDTO) {
        return Optional.of(partyRepository.save(partyDTO));
    }

    @Override
    public Optional<PartyEntity> update(PartyEntity partyDTO) {
        return save(partyDTO);
    }

    @Override
    public void delete(PartyDTO partyDTO) {
        partyRepository.delete(partyDTO.toEntity());
    }

    @Override
    public Boolean isExistParty(PartyDTO partyDTO) {
        return partyRepository.existsByPartyName(partyDTO.getPartyName());
    }

    @Override
    public List<PartyEntity> findByOwnerUsername(String ownerUsername) {
        Optional<UserLoginEntity> result = userRepository.findByUserLoginUsername(ownerUsername);

        if(result.isPresent()){
            return partyRepository.findAllByOwnerUserLoginEntity(result.get());
        } else {
            throw new NullPointerException("UserLoginEntity is not exist");
        }
    }

    @Override
    public Boolean addUserToParty(PartyDTO partyDTO, String addTargetUsername) {
        /**
         * d
         */
        PartyEntity party = partyRepository.findByPartyName(partyDTO.getPartyName());
        logger.info("party: {}", party.toString());
        Optional<UserLoginEntity> result = userRepository.findByUserLoginUsername(addTargetUsername);
        party.getUserLoginEntities().add(result.get());
        partyRepository.save(party);
        return null;
    }
}
