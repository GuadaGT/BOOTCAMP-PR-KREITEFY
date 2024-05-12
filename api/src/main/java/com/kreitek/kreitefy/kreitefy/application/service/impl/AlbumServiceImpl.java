package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.AlbumDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.AlbumMapper;
import com.kreitek.kreitefy.kreitefy.application.service.AlbumService;
import com.kreitek.kreitefy.kreitefy.domain.entity.Album;
import com.kreitek.kreitefy.kreitefy.domain.persistence.AlbumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumPersistence albumPersistence;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumPersistence albumPersistence, AlbumMapper albumMapper) {
        this.albumPersistence = albumPersistence;
        this.albumMapper = albumMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = this.albumPersistence.findAlbums();
        return albumMapper.toDto(albums);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlbumDto> getAlbumById(Long albumId) {
        return this.albumPersistence.findAlbumById(albumId).map(albumMapper::toDto);
    }

    @Override
    public AlbumDto createAlbum(AlbumDto albumDto) {
        Album album = this.albumPersistence.saveAlbum(albumMapper.toEntity(albumDto));
        return this.albumMapper.toDto(album);
    }

    @Override
    public Optional<AlbumDto> updateAlbum(Long albumId, AlbumDto albumDto) {
        Optional<Album> existingAlbumOptional = albumPersistence.findAlbumById(albumId);
        if (existingAlbumOptional.isPresent()) {
            Album existingAlbum = existingAlbumOptional.get();
            if (albumDto.getImage() != null) {
                existingAlbum.setImage(albumDto.getImage());
            }
            Album updateAlbum = albumPersistence.saveAlbum(existingAlbum);
            return Optional.of(albumMapper.toDto(updateAlbum));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAlbumById(Long albumId) {
        this.albumPersistence.deleteAlbumById(albumId);
    }

    @Override
    @Transactional
    public AlbumDto saveSongInAlbum(AlbumDto song) {
        Album album = this.albumPersistence.saveAlbum(this.albumMapper.toEntity(song));
        return this.albumMapper.toDto(album);
    }
}
