package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.ReproductionsDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.ReproductionsMapper;
import com.kreitek.kreitefy.kreitefy.application.service.ReproductionsService;
import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.ReproductionsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReproductionsServiceImpl implements ReproductionsService {

    private final ReproductionsMapper reproductionsMapper;
    private final ReproductionsJpaRepository reproductionsRepository;

    @Autowired
    public ReproductionsServiceImpl(ReproductionsMapper reproductionsMapper, ReproductionsJpaRepository reproductionsRepository) {
        this.reproductionsMapper = reproductionsMapper;
        this.reproductionsRepository = reproductionsRepository;
    }

    @Override
    public Page<ReproductionsDto> getReproductionsByUserId(Pageable pageable, Long userId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "reproductionDate");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Object[]> reproductionsPage = reproductionsRepository.findReproductionsWithSongInfoByUserId(userId, pageable);
        return reproductionsPage.map(objArray -> reproductionsMapper.toDto((Reproductions) objArray[0]));
    }

    @Override
    public Long getReproductionsCountByUserId(Long userId) {
        return reproductionsRepository.countByUserId(userId);
    }
}
