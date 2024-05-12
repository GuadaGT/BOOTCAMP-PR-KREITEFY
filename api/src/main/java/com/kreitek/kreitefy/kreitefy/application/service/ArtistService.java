package com.kreitek.kreitefy.kreitefy.application.service;
import com.kreitek.kreitefy.kreitefy.application.dto.ArtistDto;
import java.util.List;
import java.util.Optional;

public interface ArtistService {

    List<ArtistDto> findArtists();

    Optional<ArtistDto> getArtistById(Long artistId);

    ArtistDto newArtist(ArtistDto artistDto);

    Optional<ArtistDto> updateArtist(Long artistId, ArtistDto artistDto);

    void deleteArtistById(Long artistId);
}
