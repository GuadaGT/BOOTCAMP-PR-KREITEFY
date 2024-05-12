package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.SongDto;
import com.kreitek.kreitefy.kreitefy.application.dto.SongSimpleDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.SongMapper;
import com.kreitek.kreitefy.kreitefy.application.mapper.SongSimpleMapper;
import com.kreitek.kreitefy.kreitefy.application.service.SongService;
import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import com.kreitek.kreitefy.kreitefy.domain.persistence.SongPersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongPersistence songPersistence;
    private final SongMapper songMapper;
    private final SongSimpleMapper  songSimpleMapper;
    private static final int pageNumber = 0;
    private static final int pageSize = 5;

    public SongServiceImpl(SongPersistence songPersistence, SongMapper songMapper, SongSimpleMapper songSimpleMapper) {
        this.songPersistence = songPersistence;
        this.songMapper = songMapper;
        this.songSimpleMapper = songSimpleMapper;
    }

    @Override
    public Page<SongSimpleDto> getAllSongs(Pageable pageable, String search) {
        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Song> songPage = this.songPersistence.getAllSongs(pageable, search);
        return songPage.map(songSimpleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SongSimpleDto> getSongsByCriteria(Pageable pageable, String search) {
        Pageable pageableOrder = PageRequest.of(pageNumber,pageSize, Sort.by("date").descending());
        Page<Song> recentSongsPage = this.songPersistence.getAllSongsByCriteria(pageableOrder, search);
        return recentSongsPage.map(songSimpleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SongDto> getSongById(Long songId) {
        return this.songPersistence.findSongById(songId).map(songMapper::toDto);
    }

    @Override
    @Transactional
    public SongDto createSong(SongDto songDto) {
        Song saveSong = this.songPersistence.saveSong(this.songMapper.toEntity(songDto));
        return this.songMapper.toDto(saveSong);
    }

    @Override
    @Transactional
    public void addReproductionSong(Long songId) {
        SongDto songDto = this.getSongById(songId).orElseThrow(()->new RuntimeException("User not found"));
        Song song = songMapper.toEntity(songDto);
        song.setReproductions(song.getReproductions() + 1);
        songPersistence.saveSong(song);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SongSimpleDto> getSongsByReproductions(Pageable pageable,String search) {
        Pageable pageableOrder = PageRequest.of(pageNumber,pageSize, Sort.by("reproductions").descending());
        List<Song> songs = this.songPersistence.getAllSongsByCriteria(pageableOrder, search).getContent();
        return songSimpleMapper.toDto(songs);
    }

    @Override
    @Transactional
    public void addRatingSong(Long songId) {
        SongDto songDto = this.getSongById(songId).orElseThrow(()->new RuntimeException("User not found"));
        Song song = songMapper.toEntity(songDto);
        song.setRating(song.getRating());
        songPersistence.saveSong(song);
    }

    @Override
    @Transactional
    public Optional<SongSimpleDto> updateSong(Long songId, SongSimpleDto songSimpleDto) {
        Optional<Song> existingSongOptional = songPersistence.findSongById(songId);
        if (existingSongOptional.isPresent()) {
            Song existingSong = existingSongOptional.get();
            if (songSimpleDto.getImage() != null) {
                existingSong.setImageAlbum(songSimpleDto.getImage());
            }
            Song updateStyle = songPersistence.saveSong(existingSong);
            return Optional.of(songSimpleMapper.toDto(updateStyle));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void deleteSongById(Long songId) {
        this.songPersistence.deleteSongById(songId);
    }
}
