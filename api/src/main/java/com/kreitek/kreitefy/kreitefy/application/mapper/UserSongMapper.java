package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.UserSongDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.UserSong;
import com.kreitek.kreitefy.kreitefy.domain.entity.associative.UserSongAssociative;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SongMapper.class})
public interface UserSongMapper extends EntityMapper<UserSongDto, UserSong> {

    @Override
    @Mapping(source = "song.id", target = "songId")
    @Mapping(source = "user.id", target = "userId")
    UserSongDto toDto(UserSong userSong);


    @Mapping(source = "songId", target = "song")
    @Mapping(source = "songId", target = "id.songId")
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "userId", target = "id.userId")
    UserSong toEntity(UserSongDto dto);

    default UserSong fromId(UserSongAssociative id) {
        if (id == null) {
            return null;
        }
        UserSong userSong = new UserSong();
        userSong.setId(id);
        return userSong;
    }
}
