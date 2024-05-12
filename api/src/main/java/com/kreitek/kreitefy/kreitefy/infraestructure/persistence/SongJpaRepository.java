package com.kreitek.kreitefy.kreitefy.infraestructure.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SongJpaRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {
}
