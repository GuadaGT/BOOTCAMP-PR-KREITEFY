package com.kreitek.kreitefy.kreitefy.infraestructure.persistence.impl;

import com.kreitek.kreitefy.kreitefy.domain.entity.Album;
import com.kreitek.kreitefy.kreitefy.domain.persistence.AlbumPersistence;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.AlbumJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumPersistenceImpl implements AlbumPersistence {

    private final AlbumJpaRepository albumRepository;

    @Autowired
    public AlbumPersistenceImpl(AlbumJpaRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAlbums() {
        return this.albumRepository.findAll();
    }

    @Override
    public Optional<Album> findAlbumById(Long albumId) {
        return this.albumRepository.findById(albumId);
    }

    @Override
    public void deleteAlbumById(Long albumId) {
        this.albumRepository.deleteById(albumId);
    }

    @Override
    public Album saveAlbum(Album album) {
        return this.albumRepository.save(album);
    }
}
