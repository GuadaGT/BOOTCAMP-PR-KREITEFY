package com.kreitek.kreitefy.kreitefy.infraestructure.rest;

import com.kreitek.kreitefy.kreitefy.application.dto.AlbumDto;
import com.kreitek.kreitefy.kreitefy.application.dto.ArtistDto;
import com.kreitek.kreitefy.kreitefy.application.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AlbumRestController {

    private final AlbumService albumService;

    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/albums", produces = "application/json")
    ResponseEntity<List<AlbumDto>> getAllAlbums() {
        List<AlbumDto> albums = albumService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping(value = "/albums/{albumId}", produces = "application/json")
    ResponseEntity<AlbumDto> getAlbum(@PathVariable Long albumId) {
        return albumService.getAlbumById(albumId)
                .map(albumDto -> new ResponseEntity<>(albumDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/albums", produces = "application/json", consumes = "application/json")
    ResponseEntity<AlbumDto> newAlbum(@RequestBody AlbumDto albumDto) {
        return new ResponseEntity<>(albumService.createAlbum(albumDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/albums/{albumId}", produces = "application/json", consumes = "application/json")
    ResponseEntity<AlbumDto> updateAlbum(@PathVariable Long albumId, @RequestBody AlbumDto albumDto) {
        Optional<AlbumDto> existingAlbum = albumService.updateAlbum(albumId, albumDto);
        if (existingAlbum.isPresent()) {
            return new ResponseEntity<>(existingAlbum.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/albums/{albumId}", produces = "application/json")
    ResponseEntity<Void> deleteAlbumFromId(@PathVariable Long albumId) {
        albumService.deleteAlbumById(albumId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
