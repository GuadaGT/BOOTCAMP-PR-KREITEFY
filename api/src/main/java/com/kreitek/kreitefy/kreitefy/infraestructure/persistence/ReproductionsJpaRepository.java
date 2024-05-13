package com.kreitek.kreitefy.kreitefy.infraestructure.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReproductionsJpaRepository extends JpaRepository<Reproductions, Long> {

    Page<Reproductions> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT COUNT(r) FROM Reproductions r WHERE r.user.id = ?1")
    Long countByUserId(Long userId);

    @Query("SELECT r, r.song.title, r.song.artist.name, r.song.album.title FROM Reproductions r WHERE r.user.id = ?1")
    Page<Object[]> findReproductionsWithSongInfoByUserId(Long userId, Pageable pageable);
}
