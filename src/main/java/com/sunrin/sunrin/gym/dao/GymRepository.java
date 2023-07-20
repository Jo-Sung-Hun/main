package com.sunrin.sunrin.gym.dao;

import com.sunrin.sunrin.gym.domain.GymEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GymRepository extends JpaRepository<GymEntity, UUID> {
}
