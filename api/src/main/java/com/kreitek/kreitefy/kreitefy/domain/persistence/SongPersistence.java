package com.kreitek.kreitefy.kreitefy.domain.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SongPersistence {

    Page<Song> getAllSongs(Pageable pageable, String search);

    Page<Song> getAllSongsByCriteria(Pageable pageable, String search);

    Optional<Song> findSongById(Long songId);

    Song saveSong(Song song);

    void deleteSongById(Long songId);
}
