package com.kreitek.kreitefy.kreitefy.application.service;
import com.kreitek.kreitefy.kreitefy.application.dto.AlbumDto;
import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<AlbumDto> getAllAlbums();

    Optional<AlbumDto> getAlbumById(Long albumId);

    AlbumDto createAlbum(AlbumDto albumDto);

    Optional<AlbumDto> updateAlbum(Long albumId, AlbumDto albumDto);

    void deleteAlbumById(Long albumId);

    AlbumDto saveSongInAlbum(AlbumDto song);
}
