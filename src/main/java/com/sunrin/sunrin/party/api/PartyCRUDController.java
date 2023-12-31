package com.sunrin.sunrin.party.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrin.sunrin.global.auth.dao.UserRepository;
import com.sunrin.sunrin.global.auth.domain.StopWatchTarget;
import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import com.sunrin.sunrin.global.util.JwtUtil;
import com.sunrin.sunrin.party.application.PartyCRUDServiceImpl;
import com.sunrin.sunrin.party.domain.PartyEntity;
import com.sunrin.sunrin.party.domain.StopWatch;
import com.sunrin.sunrin.party.dto.PartyDTO;
import com.sunrin.sunrin.party.dto.PartyJoinDTO;
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

import java.util.List;
import java.util.Optional;

@RestController
public class PartyCRUDController {
    private static final Logger logger = LoggerFactory.getLogger(PartyCRUDController.class);
    private final ObjectMapper objectMapper;
    private final PartyCRUDServiceImpl partyCRUDService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public PartyCRUDController(ObjectMapper objectMapper, PartyCRUDServiceImpl partyCRUDService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.partyCRUDService = partyCRUDService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    // 전체 파티 정보 자겨오기
    @RequestMapping(value = "/api/v1/party", method = RequestMethod.GET)
    public Object getParty(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(partyCRUDService.findAll()), HttpStatus.OK);
    }
    // 파티 참가
    @RequestMapping(value = "/api/v1/party/join", method = RequestMethod.POST)
    public Object joinParty(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        PartyJoinDTO party = objectMapper.readValue(json, PartyJoinDTO.class);
        if (httpServletRequest.getHeader("Authorization").startsWith("Bearer")){
            String result = httpServletRequest.getHeader("Authorization").substring(7);

            logger.info("Bearer {}" , result);
            Optional<UserLoginEntity> findResult = userRepository.findByUserLoginUsername(jwtUtil.getUsernameFromToken(result));
            partyCRUDService.addUserToParty(party, findResult.get().getUserLoginUsername());
        }
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(party), HttpStatus.OK);
    }
    @RequestMapping(value = "/api/v1/party/del", method = RequestMethod.POST)
    public Object deleteByUuid(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        PartyJoinDTO party = objectMapper.readValue(json, PartyJoinDTO.class);
        PartyEntity party1 = partyCRUDService.deleteByUuid(party.getJoinTargetPartyUUID());
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(party1), HttpStatus.OK);

    }

    // 파티 생성하기
    @RequestMapping(value = "/api/v1/party", method = RequestMethod.POST)
    public Object createParty(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        PartyEntity party = objectMapper.readValue(json, PartyDTO.class).toEntity();
        if (httpServletRequest.getHeader("Authorization").startsWith("Bearer")){
            String result = httpServletRequest.getHeader("Authorization").substring(7);

            logger.info("Bearer {}" , result);
            Optional<UserLoginEntity> findResult = userRepository.findByUserLoginUsername(jwtUtil.getUsernameFromToken(result));
            party.setOwnerUsername(findResult.get().getUserLoginUsername());
            partyCRUDService.update(party);
        }
        logger.info("party {}", party);
        partyCRUDService.save(party);
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(party), HttpStatus.OK);
    }
    // 2023 07월 20일 21시 23분.
    // 지금까지 코드는 개발자 본인과 신만이 알았다.
    // 하지만 이제는.
    // 신만이 알고있는 코드를 작성ㅎ
    @RequestMapping(value = "/api/v1/user/stopwatch" , method = RequestMethod.GET)
    public Object getParty(HttpServletRequest httpServletRequest, @RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        StopWatchTarget party = objectMapper.readValue(json, StopWatchTarget.class);
        Optional<UserLoginEntity> result = userRepository.findByUserLoginUsername(party.getUsername());
        return result.get().getStopwatchLocalDateTime();
    }
    @RequestMapping(value = "/api/v1/user/stopwatch", method = RequestMethod.POST)
    public Object addParty(@RequestBody String json) throws JsonProcessingException {
        logger.info("json {}", json);
        StopWatch party = objectMapper.readValue(json, StopWatch.class);
        Optional<UserLoginEntity> result = userRepository.findByUserLoginUsername(party.getTargetUserUsername());
        result.get().setStopwatchLocalDateTime(party.getTime());
        userRepository.save(result.get());

        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result)), HttpStatus.OK);
    }
    @RequestMapping(value = "/api/v1/user/stopwatch/findall", method = RequestMethod.GET)
    public Object findAllParty() throws JsonProcessingException {
        List<UserLoginEntity> userLoginEntities = userRepository.findAll();
        return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginEntities), HttpStatus.OK);
    }

}
