package com.kreitek.kreitefy.kreitefy.infraestructure.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlbumJpaRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {
}
