package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.UserSimpleDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.UserSimpleMapper;
import com.kreitek.kreitefy.kreitefy.application.service.AuthService;
import com.kreitek.kreitefy.kreitefy.domain.entity.User;
import com.kreitek.kreitefy.kreitefy.domain.persistence.UserPersistence;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserPersistence userPersistence;
    private final UserSimpleMapper userSimpleMapper;

    public AuthServiceImpl(UserPersistence userPersistence, UserSimpleMapper userSimpleMapper) {
        this.userPersistence = userPersistence;
        this.userSimpleMapper = userSimpleMapper;
    }

    @Override
    public UserSimpleDto register(UserSimpleDto userSimpleDto) {
        User user = userSimpleMapper.toEntity(userSimpleDto);
        return userSimpleMapper.toDto(userPersistence.saveUser(user));
    }

    @Override
    public Optional<UserSimpleDto> getUser(String username) {
        Optional<User> user = userPersistence.findUserByName(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userSimpleMapper.toDto(user.get()));
    }
}

