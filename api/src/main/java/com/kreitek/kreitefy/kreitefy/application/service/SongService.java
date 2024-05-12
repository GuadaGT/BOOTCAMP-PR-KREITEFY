package com.kreitek.kreitefy.kreitefy.application.service;

import com.kreitek.kreitefy.kreitefy.application.dto.SongDto;
import com.kreitek.kreitefy.kreitefy.application.dto.SongSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongService {

    Page<SongSimpleDto> getAllSongs(Pageable pageable, String search);

    Page<SongSimpleDto> getSongsByCriteria(Pageable pageable, String search);

    Optional<SongDto> getSongById(Long songId);

    SongDto createSong(SongDto songDto);

    void addReproductionSong(Long songId);

    List<SongSimpleDto> getSongsByReproductions(Pageable pageable, String search);

    void addRatingSong(Long songId);

    Optional<SongSimpleDto> updateSong(Long songId, SongSimpleDto songSimpleDto);

    void deleteSongById(Long songId);
}
