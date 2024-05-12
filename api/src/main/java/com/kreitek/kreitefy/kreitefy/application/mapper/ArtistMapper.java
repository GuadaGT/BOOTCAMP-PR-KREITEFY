package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.ArtistDto;

import com.kreitek.kreitefy.kreitefy.domain.entity.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SongMapper.class})
public interface ArtistMapper extends EntityMapper<ArtistDto, Artist> {

    @Mapping(target ="songs", ignore = true)
    Artist toEntity(ArtistDto dto);
    ArtistDto toDto(Artist entity);

    default Artist fromId(Long id) {
        if(id == null) return null;
        Artist artist = new Artist();
        artist.setId(id);
        return artist;
    }
}
