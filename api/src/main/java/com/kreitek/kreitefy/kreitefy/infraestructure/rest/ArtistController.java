package com.kreitek.kreitefy.kreitefy.infraestructure.rest;

import com.kreitek.kreitefy.kreitefy.application.dto.ArtistDto;
import com.kreitek.kreitefy.kreitefy.application.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(value = "/artists", produces = "application/json")
    ResponseEntity<List<ArtistDto>> getAllArtists() {
        List<ArtistDto> artists = this.artistService.findArtists();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping(value = "/artists/{artistId}", produces = "application/json")
    ResponseEntity<ArtistDto> getArtist(@PathVariable Long artistId) {
        return artistService.getArtistById(artistId)
                .map(artistDto -> new ResponseEntity<>(artistDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/artists", produces = "application/json", consumes = "application/json")
    ResponseEntity<ArtistDto> newArtist(@RequestBody ArtistDto artistDto) {
        return new ResponseEntity<>(artistService.newArtist(artistDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/artists", produces = "application/json", consumes = "application/json")
    ResponseEntity<ArtistDto> updateArtist(@PathVariable Long artistId, @RequestBody ArtistDto artistDto) {
        Optional<ArtistDto> existingArtist = this.artistService.updateArtist(artistId, artistDto);
        if (existingArtist.isPresent()) {
            return new ResponseEntity<>(existingArtist.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/artists/{artistId}", produces = "application/json")
    ResponseEntity<Void> deleteArtistFromId(@PathVariable Long artistId) {
        artistService.deleteArtistById(artistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
