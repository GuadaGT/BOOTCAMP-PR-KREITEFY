package com.kreitek.kreitefy.kreitefy.infraestructure.persistence.impl;

import com.kreitek.kreitefy.kreitefy.domain.entity.Style;
import com.kreitek.kreitefy.kreitefy.domain.persistence.StylePersistence;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.StyleJpaRepository;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteria;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.shared.SearchCriteriaHelper;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.specifications.SongSpecification;
import com.kreitek.kreitefy.kreitefy.infraestructure.specs.specifications.StyleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StylePersistenceImpl implements StylePersistence {

    private final StyleJpaRepository styleRepository;

    @Autowired
    public StylePersistenceImpl(StyleJpaRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public List<Style> getAllStyles() {
        return this.styleRepository.findAll();
    }

    @Override
    public Page<Style> getAllStylesByCriteria(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            List<SearchCriteria> criteriaList = SearchCriteriaHelper.fromFilterString(search);
            StyleSpecification specification = new StyleSpecification(criteriaList);
            return styleRepository.findAll(specification, pageable);
        } else {
            return styleRepository.findAll(pageable);
        }
    }

    @Override
    public Optional<Style> getStyleById(Long styleId) {
        return this.styleRepository.findById(styleId);
    }

    @Override
    public Style createStyle(Style style) {
        return this.styleRepository.save(style);
    }

    @Override
    public void deleteStyle(Long styleId) {
        this.styleRepository.deleteById(styleId);
    }
}
