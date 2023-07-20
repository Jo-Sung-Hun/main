package com.sunrin.sunrin.gym.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrin.sunrin.gym.application.GymCRUDServiceImpl;
import com.sunrin.sunrin.gym.dto.GymEntityDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GymController {
    private static final Logger logger = LoggerFactory.getLogger(GymController.class);
    private final ObjectMapper objectMapper;
    private final GymCRUDServiceImpl gymCRUDService;
    @Autowired
    public GymController(ObjectMapper objectMapper, GymCRUDServiceImpl gymCRUDService) {
        this.objectMapper = objectMapper;
        this.gymCRUDService = gymCRUDService;
    }

    @RequestMapping(value = "/api/v1/gym", method = RequestMethod.GET)
    public Object getGymList(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(gymCRUDService.findAll(), org.springframework.http.HttpStatus.OK);
    }
    @RequestMapping(value = "/api/v1/gym ", method = RequestMethod.POST)
    public Object addGym(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        GymEntityDTO gymEntityDTO = objectMapper.readValue(json, GymEntityDTO.class);
        logger.info("gym Data: {}" ,gymEntityDTO.toString());
        return new ResponseEntity<>(gymCRUDService.save(gymEntityDTO), org.springframework.http.HttpStatus.OK);
    }
    @RequestMapping(value = "/api/v1/gym/findbyid", method = RequestMethod.GET)
    public Object getGym(HttpServletRequest httpServletRequest, @RequestParam("joinTargetPartyUUID") String joinTargetPartyUUID) throws JsonProcessingException {
        return new ResponseEntity<>(gymCRUDService.findById(UUID.fromString(joinTargetPartyUUID)), org.springframework.http.HttpStatus.OK);
    }
}
