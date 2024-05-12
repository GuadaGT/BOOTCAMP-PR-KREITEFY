package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.AlbumDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumMapper extends EntityMapper<AlbumDto, Album> {

    @Mapping(target ="songs", ignore = true)
    Album toEntity(AlbumDto dto);
    AlbumDto toDto(Album entity);

    default Album fromId(Long id) {
        if(id == null) return null;
        Album album = new Album();
        album.setId(id);
        return album;
    }
}
