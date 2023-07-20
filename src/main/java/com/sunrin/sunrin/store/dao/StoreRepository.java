package com.sunrin.sunrin.store.dao;

import com.sunrin.sunrin.store.domain.StoreComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreComponent, UUID> {
    List<StoreComponent> findAllByMakerAndName(String maker, String name);
    List<StoreComponent> findAllByCategory(String category);
}
