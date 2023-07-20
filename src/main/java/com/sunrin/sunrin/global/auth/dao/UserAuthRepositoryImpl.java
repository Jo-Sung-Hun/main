package com.sunrin.sunrin.global.auth.dao;

import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserAuthRepositoryImpl.class);
    public UserAuthRepositoryImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserLoginEntity> findByEmail(String email) {
        if (email == null) {
            throw new NullPointerException("email is null");
        }
        return userRepository.findByUserLoginUsername(email);
    }


}