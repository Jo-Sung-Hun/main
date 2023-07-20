package com.sunrin.sunrin.gym.dto;


import com.sunrin.sunrin.gym.domain.GymEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymEntityDTO {
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


    public GymEntity toEntity() {
        return GymEntity.builder()
                .name(name)
                .description(description)
                .phoneNumber(phoneNumber)
                .address(address)
                .businessDay(businessDay)
                .imgUri(imgUri)
                .star(star)
                .tag1(tag1)
                .tag2(tag2)
                .tag3(tag3)
                .build();
    }
}
