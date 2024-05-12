package com.kreitek.kreitefy.kreitefy.domain.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArtistPersistence {

    List<Artist> findAll();

    Optional<Artist> findArtistById(Long artistId);

    Artist saveArtist(Artist artist);

    void deleteArtistById(Long artistId);
}
