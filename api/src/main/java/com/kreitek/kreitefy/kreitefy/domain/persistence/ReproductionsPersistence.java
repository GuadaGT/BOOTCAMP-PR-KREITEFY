package com.kreitek.kreitefy.kreitefy.domain.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReproductionsPersistence {

    Page<Reproductions> getReproductionsByUser(Long userId, Pageable pageable);

}
