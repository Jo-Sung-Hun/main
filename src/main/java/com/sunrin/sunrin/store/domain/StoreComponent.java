package com.sunrin.sunrin.store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class StoreComponent implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;
    private String category;
    private String name;
    private String price;
    private String iamgeUrl;

    @Builder
    public StoreComponent(String category, String name, String price, String iamgeUrl) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.iamgeUrl = iamgeUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public StoreComponent() {
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

    public String getPrice() {
        return price;
    }

    public String getIamgeUrl() {

        return iamgeUrl;
    }

    public void setIamgeUrl(String iamgeUrl) {
        this.iamgeUrl = iamgeUrl;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
