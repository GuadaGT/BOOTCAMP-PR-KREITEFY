package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.ReproductionsDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.ReproductionsMapper;
import com.kreitek.kreitefy.kreitefy.application.service.ReproductionsService;
import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import com.kreitek.kreitefy.kreitefy.domain.persistence.ReproductionsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReproductionsServiceImpl implements ReproductionsService {
    private final ReproductionsPersistence reproductionsPersistence;
    private final ReproductionsMapper reproductionsMapper;

    @Autowired
    public ReproductionsServiceImpl(ReproductionsPersistence reproductionsPersistence, ReproductionsMapper reproductionsMapper) {
        this.reproductionsPersistence = reproductionsPersistence;
        this.reproductionsMapper = reproductionsMapper;
    }

    @Override
    public Page<ReproductionsDto> getReproductionsByUserId(Long userId, Pageable pageable) {
        Page<Reproductions> reproductionsPage = reproductionsPersistence.getReproductionsByUser(userId, pageable);
        return reproductionsPage.map(reproductionsMapper::toDto);
    }
}
