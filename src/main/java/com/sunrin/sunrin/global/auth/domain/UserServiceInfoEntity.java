package com.sunrin.sunrin.global.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserServiceInfoEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

}
