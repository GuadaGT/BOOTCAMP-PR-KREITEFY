package com.kreitek.kreitefy.kreitefy.application.service;

import com.kreitek.kreitefy.kreitefy.application.dto.UserDto;

import java.util.Optional;

public interface UserService {

    UserDto addUser(UserDto userDto);
    Optional<UserDto> updateUser(Long userId, UserDto userDto);
    Optional<UserDto> getUserByUsername(String username);
    Optional<UserDto> getUserById(Long userId);
    void deleteUser(Long userId);
}
