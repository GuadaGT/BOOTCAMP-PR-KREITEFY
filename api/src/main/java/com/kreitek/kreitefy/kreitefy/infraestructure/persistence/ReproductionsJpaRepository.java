package com.kreitek.kreitefy.kreitefy.infraestructure.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReproductionsJpaRepository extends JpaRepository<Reproductions, Long> {

    Page<Reproductions> findByUserId(Long userId, Pageable pageable);
}
