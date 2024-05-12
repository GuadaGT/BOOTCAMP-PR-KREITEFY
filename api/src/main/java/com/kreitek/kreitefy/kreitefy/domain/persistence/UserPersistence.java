package com.kreitek.kreitefy.kreitefy.domain.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.User;

import java.util.Optional;

public interface UserPersistence {

    User saveUser(User user);

    Optional<User> findUserByName(String username);

    Optional<User> findUserById(Long userId);

    void deleteUserById(Long userId);
}
