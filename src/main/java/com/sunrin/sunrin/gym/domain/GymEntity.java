package com.sunrin.sunrin.gym.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class GymEntity {
    @Id
    private UUID uuid;
    private String name;

}
