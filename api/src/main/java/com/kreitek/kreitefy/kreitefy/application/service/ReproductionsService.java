package com.kreitek.kreitefy.kreitefy.application.service;

import com.kreitek.kreitefy.kreitefy.application.dto.ReproductionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReproductionsService {

    Page<ReproductionsDto> getReproductionsByUserId(Pageable pageable, Long userId);

    Long getReproductionsCountByUserId(Long userId);
}
