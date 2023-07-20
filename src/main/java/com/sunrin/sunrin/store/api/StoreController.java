package com.sunrin.sunrin.store.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrin.sunrin.global.util.JwtUtil;
import com.sunrin.sunrin.store.application.StoreServiceImpl;
import com.sunrin.sunrin.store.domain.StoreComponent;
import com.sunrin.sunrin.store.dto.StoreDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
    private final StoreServiceImpl storeService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    @Autowired
    public StoreController(StoreServiceImpl storeService, ObjectMapper objectMapper, JwtUtil jwtUtil) {
        this.storeService = storeService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/api/v1/store", method = RequestMethod.POST)
    public Object createStoreComponent(@RequestBody String json, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String jwtToken = httpServletRequest.getHeader("Authorization").substring(7);
        logger.info("jwtToken {}", jwtToken);
        StoreComponent storeComponent = objectMapper.readValue(json, StoreComponent.class);
        storeComponent.setMaker(jwtUtil.getUsernameFromToken(jwtToken));
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(storeService.create(storeComponent)), org.springframework.http.HttpStatus.OK);
    }
    @RequestMapping(value = "/api/v1/store", method = RequestMethod.GET)
    public Object getStoreComponent(@RequestBody String json) throws JsonProcessingException {
        StoreDTO storeDTO = objectMapper.readValue(json, StoreDTO.class);
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(storeService.findByCategory(storeDTO.getCategory())), org.springframework.http.HttpStatus.OK);
    }
    @RequestMapping(value = "/api/v1/store/findall", method = RequestMethod.GET)

    public Object getStoreComponentAll() throws JsonProcessingException {
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(storeService.findAll()), org.springframework.http.HttpStatus.OK);
    }
}
