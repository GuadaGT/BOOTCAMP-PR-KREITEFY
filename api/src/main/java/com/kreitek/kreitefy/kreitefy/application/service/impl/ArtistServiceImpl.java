package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.ArtistDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.ArtistMapper;
import com.kreitek.kreitefy.kreitefy.application.service.ArtistService;
import com.kreitek.kreitefy.kreitefy.domain.entity.Artist;
import com.kreitek.kreitefy.kreitefy.domain.persistence.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistPersistence artistPersistence;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistServiceImpl(ArtistPersistence artistPersistence, ArtistMapper artistMapper) {
        this.artistPersistence = artistPersistence;
        this.artistMapper = artistMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArtistDto> findArtists() {
        List<Artist> artists = this.artistPersistence.findAll();
        return artistMapper.toDto(artists);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArtistDto> getArtistById(Long artistId) {
        return this.artistPersistence.findArtistById(artistId).map(artistMapper::toDto);
    }

    @Override
    @Transactional
    public ArtistDto newArtist(ArtistDto artistDto) {
        Artist artist = this.artistPersistence.saveArtist(artistMapper.toEntity(artistDto));
        return this.artistMapper.toDto(artist);
    }

    @Override
    public Optional<ArtistDto> updateArtist(Long artistId, ArtistDto artistDto) {
        Optional<Artist> existingArtistOptional = artistPersistence.findArtistById(artistId);
        if (existingArtistOptional.isPresent()) {
            Artist existingArtist = existingArtistOptional.get();
            if (artistDto.getName() != null) {
                existingArtist.setName(artistDto.getName());
            }
            Artist updateArtist = artistPersistence.saveArtist(existingArtist);
            return Optional.of(artistMapper.toDto(updateArtist));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void deleteArtistById(Long artistId) {
        this.artistPersistence.deleteArtistById(artistId);
    }
}
