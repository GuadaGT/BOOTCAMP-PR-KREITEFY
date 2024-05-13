package com.kreitek.kreitefy.kreitefy.infraestructure.persistence.impl;

import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import com.kreitek.kreitefy.kreitefy.domain.persistence.ReproductionsPersistence;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.ReproductionsJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ReproductionsPersistenceImpl implements ReproductionsPersistence {

    private final ReproductionsJpaRepository reproductionsJpaRepository;

    public ReproductionsPersistenceImpl(ReproductionsJpaRepository reproductionsJpaRepository) {
        this.reproductionsJpaRepository = reproductionsJpaRepository;
    }

    @Override
    public Page<Reproductions> getReproductionsByUser(Long userId, Pageable pageable) {
        return reproductionsJpaRepository.findByUserId(userId, pageable);
    }
}
