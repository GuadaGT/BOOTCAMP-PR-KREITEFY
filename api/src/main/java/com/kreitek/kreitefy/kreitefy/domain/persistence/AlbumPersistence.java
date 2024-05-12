package com.kreitek.kreitefy.kreitefy.domain.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Album;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlbumPersistence {

    List<Album> findAlbums();

    Optional<Album> findAlbumById(Long albumId);

    void deleteAlbumById(Long albumId);

    Album saveAlbum(Album album);
}
