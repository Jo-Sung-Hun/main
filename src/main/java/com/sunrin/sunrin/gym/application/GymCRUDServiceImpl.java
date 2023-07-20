package com.sunrin.sunrin.gym.application;

import com.sunrin.sunrin.gym.dao.GymRepository;
import com.sunrin.sunrin.gym.domain.GymEntity;
import com.sunrin.sunrin.gym.dto.GymEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GymCRUDServiceImpl {
    private final GymRepository gymRepository;
    @Autowired
    public GymCRUDServiceImpl(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<GymEntity> findAll() {
        return gymRepository.findAll();
    }
    public GymEntity save(GymEntityDTO gymEntityDTO){
        return gymRepository.save(gymEntityDTO.toEntity());
    }
    public GymEntity findById(UUID uuid){
        return gymRepository.findById(uuid).orElseThrow(() -> new NullPointerException("GymEntity is not exist"));
    }
}
