package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.SongDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.Artist;
import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {StyleMapper.class, AlbumMapper.class, ArtistMapper.class})
public interface SongMapper extends EntityMapper<SongDto, Song> {

    @Override
    @Mapping(source = "artistId", target = "artist.id")
    @Mapping(source = "artistName", target = "artist.name")
    @Mapping(source = "albumId", target = "album.id")
    @Mapping(source = "album", target = "album.title")
    @Mapping(source = "imageAlbum", target = "album.image")
    @Mapping(source = "styleId", target = "style.id")
    @Mapping(source = "style", target = "style.style")
    @Mapping(target = "userSongs", ignore = true)
    Song toEntity(SongDto dto);

    @Override
    @Mapping(source = "artist.id", target = "artistId")
    @Mapping(source = "artist.name", target = "artistName")
    @Mapping(source = "album.id", target = "albumId")
    @Mapping(source = "album.title", target = "album")
    @Mapping(source = "album.image", target = "imageAlbum")
    @Mapping(source = "style.id", target = "styleId")
    @Mapping(source = "style.style", target = "style")
    SongDto toDto(Song entity);

    default Song fromId(Long id) {
        if (id == null) {
            return null;
        }
        Song song = new Song();
        song.setId(id);
        return song;
    }
    @Named("map")
    default Artist map(String value) {
        if (value == null) {
            return null;
        }
        Artist artist = new Artist();
        artist.setName(value);
        return artist;
    }
}
