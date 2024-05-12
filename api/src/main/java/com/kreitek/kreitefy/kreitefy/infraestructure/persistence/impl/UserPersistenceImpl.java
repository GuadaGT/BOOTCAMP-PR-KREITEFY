package com.kreitek.kreitefy.kreitefy.infraestructure.persistence.impl;

import com.kreitek.kreitefy.kreitefy.domain.entity.User;
import com.kreitek.kreitefy.kreitefy.domain.persistence.UserPersistence;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistenceImpl implements UserPersistence {

    private final UserJpaRepository userJpaRepository;

    public UserPersistenceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User saveUser(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findUserByName(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return this.userJpaRepository.findById(userId);
    }


    @Override
    public void deleteUserById(Long userId) {
        this.userJpaRepository.deleteById(userId);
    }
}
