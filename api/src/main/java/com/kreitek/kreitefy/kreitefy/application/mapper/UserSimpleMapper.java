package com.kreitek.kreitefy.kreitefy.application.mapper;
import com.kreitek.kreitefy.kreitefy.application.dto.UserSimpleDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserSimpleMapper extends EntityMapper<UserSimpleDto, User> {
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userSongs", ignore = true)
    User toEntity(UserSimpleDto dto);

    @Override
    UserSimpleDto toDto(User entity);

    default User fromId(String username) {
        if (username == null) {
            return null;
        }
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
