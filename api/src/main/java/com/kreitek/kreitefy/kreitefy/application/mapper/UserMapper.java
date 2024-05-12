package com.kreitek.kreitefy.kreitefy.application.mapper;
import com.kreitek.kreitefy.kreitefy.application.dto.UserDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    @Override
    @Mapping(target = "userSongs", ignore = true)
    User toEntity(UserDto dto);

    @Override
    UserDto toDto(User entity);

    default User fromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
