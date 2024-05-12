package com.kreitek.kreitefy.kreitefy.infraestructure.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StyleJpaRepository extends JpaRepository<Style, Long>, JpaSpecificationExecutor<Style> {
}
