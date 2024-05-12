package com.kreitek.kreitefy.kreitefy.application.service;

import com.kreitek.kreitefy.kreitefy.application.dto.UserSimpleDto;

import java.util.Optional;

public interface AuthService {
    public UserSimpleDto register(UserSimpleDto userSimpleDto);
    Optional<UserSimpleDto> getUser(String username);
}
