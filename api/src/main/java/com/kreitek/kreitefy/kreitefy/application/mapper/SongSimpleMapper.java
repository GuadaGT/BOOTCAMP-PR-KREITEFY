package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.SongSimpleDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.Album;
import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import com.kreitek.kreitefy.kreitefy.domain.entity.Style;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, AlbumMapper.class, StyleMapper.class, AlbumMapper.class})
public interface SongSimpleMapper extends EntityMapper<SongSimpleDto, Song> {

    @Override
    @Mapping(source = "artistId", target = "artist")
    @Mapping(source = "albumId", target = "album")
    @Mapping(source = "image", target = "album.image")
    @Mapping(source = "style", target = "style")
    Song toEntity(SongSimpleDto dto);

    @Override
    @Mapping(source = "artist.id", target = "artistId")
    @Mapping(source = "album.id", target = "albumId")
    @Mapping(source = "album.image", target = "image")
    @Mapping(source = "artist.name", target = "artistName")
    SongSimpleDto toDto(Song entity);

    default String map(Album value) {
        return value != null ? value.getTitle() : null;
    }

    default String map(Style value) {
        return value != null ? value.getStyle() : null;
    }

    default Song fromId(Long id) {
        if (id == null) return null;
        Song song = new Song();
        song.setId(id);
        return song;
    }
}