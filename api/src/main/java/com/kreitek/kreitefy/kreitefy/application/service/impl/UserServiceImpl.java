package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.UserDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.UserMapper;
import com.kreitek.kreitefy.kreitefy.application.service.UserService;
import com.kreitek.kreitefy.kreitefy.domain.entity.User;
import com.kreitek.kreitefy.kreitefy.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserPersistence userPersistence;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserPersistence userPersistence, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userPersistence = userPersistence;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
       User user = this.userPersistence.saveUser(userMapper.toEntity(userDto));
       return this.userMapper.toDto(user);
    }

    @Override
    @Transactional
    public Optional<UserDto> updateUser(Long userId, UserDto userDto) {
        Optional<User> existingUserOptional = userPersistence.findUserById(userId);
        if(existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setEmail(userDto.getEmail());
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            User updatedUser = userPersistence.saveUser(existingUser);
            return Optional.of(userMapper.toDto(updatedUser));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDto> getUserByUsername(String username) {
        return userPersistence.findUserByName(username).map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserById(Long userId) {
       return this.userPersistence.findUserById(userId).map(userMapper::toDto);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        this.userPersistence.deleteUserById(userId);
    }
}
