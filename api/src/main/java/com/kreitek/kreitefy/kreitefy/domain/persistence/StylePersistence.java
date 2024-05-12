package com.kreitek.kreitefy.kreitefy.domain.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StylePersistence {

    List<Style> getAllStyles();

    Page<Style> getAllStylesByCriteria(Pageable pageable, String search);

    Optional<Style> getStyleById(Long styleId);

    Style createStyle(Style style);

    void deleteStyle(Long styleId);
}
