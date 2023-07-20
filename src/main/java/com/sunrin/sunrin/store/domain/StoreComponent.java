package com.sunrin.sunrin.store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;

import java.util.UUID;

@Entity
public class StoreComponent {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;
    private String name;
    private String maker;
    private Double star;
    private Integer reviewNum;
    private String imageUrl;
    private String storeTag;
    private String storeTag2;
    private String category;
    @Builder
    public StoreComponent(String name, String maker, Double star, Integer reviewNum, String storeTag,String storeTag2 , String imageUrl, String category) {
        this.name = name;
        this.category = category;
        this.storeTag2 = storeTag2;
        this.maker = maker;
        this.star = star;
        this.reviewNum = reviewNum;
        this.storeTag = storeTag;
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public StoreComponent() {
    }

    public String getStoreTag2() {
        return storeTag2;
    }

    public void setStoreTag2(String storeTag2) {
        this.storeTag2 = storeTag2;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Integer getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(Integer reviewNum) {
        this.reviewNum = reviewNum;
    }

    public String getStoreTag() {
        return storeTag;
    }

    public void setStoreTag(String storeTag) {
        this.storeTag = storeTag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
