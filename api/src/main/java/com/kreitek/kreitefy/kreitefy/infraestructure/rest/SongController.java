package com.kreitek.kreitefy.kreitefy.infraestructure.rest;

import com.kreitek.kreitefy.kreitefy.application.dto.SongDto;
import com.kreitek.kreitefy.kreitefy.application.dto.SongSimpleDto;
import com.kreitek.kreitefy.kreitefy.application.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/allsongs", produces = "application/json")
    ResponseEntity<Page<SongSimpleDto>> getAllSongs(Pageable pageable, @RequestParam(value = "filter", required = false) String search) {
        Page<SongSimpleDto> songs = this.songService.getAllSongs(pageable, search);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping(value = "/songs/new", produces = "application/json")
    ResponseEntity<Page<SongSimpleDto>> getRecentSongs(Pageable pageable, @RequestParam(value = "filter", required = false) String search) {
        Page<SongSimpleDto> recentSongs = this.songService.getSongsByCriteria(pageable, search);
        return new ResponseEntity<>(recentSongs, HttpStatus.OK);
    }

    @GetMapping(value = "/songs/{songId}", produces = "application/json")
    ResponseEntity<SongDto> getSong(@PathVariable Long songId) {
        return songService.getSongById(songId)
                .map(songDto -> new ResponseEntity<>(songDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/songs/reproductions", produces = "application/json")
    ResponseEntity<List<SongSimpleDto>> getSongsReproductions(Pageable pageable,@RequestParam(value = "filter", required = false) String search) {
        List<SongSimpleDto> songs = this.songService.getSongsByReproductions(pageable,search);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping(value = "/songs", produces = "application/json", consumes = "application/json")
    ResponseEntity<SongDto> newSong(@RequestBody SongDto songDto) {
        return new ResponseEntity<>(songService.createSong(songDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/songs/{songId}", produces = "application/json", consumes = "application/json")
    ResponseEntity<SongSimpleDto> updateSong(@PathVariable Long songId, @RequestBody SongSimpleDto songSimpleDto) {
        Optional<SongSimpleDto> existingSong = this.songService.updateSong(songId, songSimpleDto);
        if (existingSong.isPresent()) {
            return new ResponseEntity<>(existingSong.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/songs/{songId}", produces = "application/json")
    ResponseEntity<Void> deleteSongFromId(@PathVariable Long songId) {
        songService.deleteSongById(songId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
