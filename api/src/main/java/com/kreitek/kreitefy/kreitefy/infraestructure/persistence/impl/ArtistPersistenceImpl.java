package com.kreitek.kreitefy.kreitefy.infraestructure.persistence.impl;

import com.kreitek.kreitefy.kreitefy.domain.entity.Artist;
import com.kreitek.kreitefy.kreitefy.domain.persistence.ArtistPersistence;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.ArtistJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistPersistenceImpl implements ArtistPersistence {

    private final ArtistJpaRepository artistRepository;

    @Autowired
    public ArtistPersistenceImpl(ArtistJpaRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() {
        return this.artistRepository.findAll();
    }

    @Override
    public Optional<Artist> findArtistById(Long artistId) {
        return this.artistRepository.findById(artistId);
    }

    @Override
    public Artist saveArtist(Artist artist) {
        return this.artistRepository.save(artist);
    }

    @Override
    public void deleteArtistById(Long artistId) {
        this.artistRepository.deleteById(artistId);
    }
}
