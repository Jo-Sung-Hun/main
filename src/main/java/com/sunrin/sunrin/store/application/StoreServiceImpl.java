package com.sunrin.sunrin.store.application;

import com.sunrin.sunrin.store.dao.StoreRepository;
import com.sunrin.sunrin.store.domain.StoreComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreComponent create(StoreComponent storeComponent) {
        return storeRepository.save(storeComponent);
    }

    @Override
    public StoreComponent update(StoreComponent storeComponent) {
        return storeRepository.save(storeComponent);
    }

    @Override
    public void delete(StoreComponent storeComponent) {
         storeRepository.delete(storeComponent);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("storeCache")
    public List<StoreComponent> findAll() {
        return storeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StoreComponent> findById(UUID id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<StoreComponent> findByStoreNameOrStoreMaker(String storeName, String storeMaker) {
        return storeRepository.findAllByMakerAndName(storeMaker, storeName);
    }

    @Override
    public List<StoreComponent> findByCategory(String category) {
        return storeRepository.findAllByCategory(category);
    }
}
