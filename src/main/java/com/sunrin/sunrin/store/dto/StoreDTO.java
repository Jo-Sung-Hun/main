package com.sunrin.sunrin.store.dto;

public class StoreDTO {
    private String category;

    public StoreDTO(String category) {
        this.category = category;
    }

    public StoreDTO() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
