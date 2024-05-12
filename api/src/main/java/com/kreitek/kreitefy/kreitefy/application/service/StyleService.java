package com.kreitek.kreitefy.kreitefy.application.service;
import com.kreitek.kreitefy.kreitefy.application.dto.StyleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StyleService {

    List<StyleDto> getAllStyles();

    Page<StyleDto> getAllStylesByCriteria(Pageable pageable, String search);

    Optional<StyleDto> getStyleById(Long styleId);

    StyleDto createStyle(StyleDto styleDto);

    Optional<StyleDto> updateStyle(Long styleId, StyleDto styleDto);

    void deleteStyleById(Long styleId);

}
