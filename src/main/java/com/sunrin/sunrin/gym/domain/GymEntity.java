package com.sunrin.sunrin.gym.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;
    private String name;
    private String description;
    private String phoneNumber;
    private String address;
    private String businessDay;
    private String imgUri;
    private Integer star;
    private String tag1;
    private String tag2;
    private String tag3;


}