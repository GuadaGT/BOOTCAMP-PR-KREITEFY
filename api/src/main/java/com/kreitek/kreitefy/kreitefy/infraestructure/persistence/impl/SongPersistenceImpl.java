package com.kreitek.kreitefy.kreitefy.infraestructure.persistence.impl;

import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import com.kreitek.kreitefy.kreitefy.domain.persistence.SongPersistence;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.SongJpaRepository;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteria;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteriaHelper;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.specifications.SongSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongPersistenceImpl implements SongPersistence {

    private final SongJpaRepository songRepository;

    @Autowired
    public SongPersistenceImpl(SongJpaRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Page<Song> getAllSongs(Pageable pageable, String search) {
        SongSpecification songSpecification = new SongSpecification(SearchCriteriaHelper.fromFilterString(search));
        return this.songRepository.findAll(Specification.where(songSpecification).and(songSpecification), pageable);
    }

    @Override
    public Page<Song> getAllSongsByCriteria(Pageable pageable, String search) {
        List<SearchCriteria> criteria = SearchCriteriaHelper.fromFilterString(search);
        SongSpecification songSpecification = new SongSpecification(criteria);
        return this.songRepository.findAll(Specification.where(songSpecification), pageable);
    }

    @Override
    public Optional<Song> findSongById(Long songId) {
        return this.songRepository.findById(songId);
    }

    @Override
    public Song saveSong(Song song) {
        return this.songRepository.save(song);
    }

    @Override
    public void deleteSongById(Long songId) {
        this.songRepository.deleteById(songId);
    }
}
