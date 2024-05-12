package com.kreitek.kreitefy.kreitefy.infraestructure.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArtistJpaRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {
}
