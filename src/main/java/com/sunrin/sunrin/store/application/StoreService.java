package com.sunrin.sunrin.store.application;

import com.sunrin.sunrin.store.domain.StoreComponent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreService {
    StoreComponent create(StoreComponent storeComponent);
    StoreComponent update(StoreComponent storeComponent);
    void delete(StoreComponent storeComponent);
    List<StoreComponent> findAll();
    Optional<StoreComponent> findById(UUID id);
    List<StoreComponent> findByStoreNameOrStoreMaker(String storeName, String storeMaker);
    List<StoreComponent> findByCategory(String category);
}
