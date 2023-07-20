package com.sunrin.sunrin.party.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrin.sunrin.party.application.PartyCRUDServiceImpl;
import com.sunrin.sunrin.party.domain.PartyEntity;
import com.sunrin.sunrin.party.dto.PartyDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PartyCRUDController {
    private static final Logger logger = LoggerFactory.getLogger(PartyCRUDController.class);
    private final ObjectMapper objectMapper;
    private final PartyCRUDServiceImpl partyCRUDService;
    @Autowired
    public PartyCRUDController(ObjectMapper objectMapper, PartyCRUDServiceImpl partyCRUDService) {
        this.objectMapper = objectMapper;
        this.partyCRUDService = partyCRUDService;
    }

    @RequestMapping(value = "/api/v1/party", method = RequestMethod.POST)
    public Object createParty(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        Optional<PartyEntity> party = partyCRUDService.save(objectMapper.readValue(json, PartyDTO.class));
        logger.info("party {}", party);
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(party.get()), HttpStatus.OK);
    }
}
