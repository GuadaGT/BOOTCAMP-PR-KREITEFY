package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.StyleDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.StyleMapper;
import com.kreitek.kreitefy.kreitefy.application.service.StyleService;
import com.kreitek.kreitefy.kreitefy.domain.entity.Style;
import com.kreitek.kreitefy.kreitefy.domain.persistence.StylePersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StyleServiceImpl implements StyleService {

    private final StylePersistence stylePersistence;
    private final StyleMapper styleMapper;

    public StyleServiceImpl(StylePersistence stylePersistence, StyleMapper styleMapper) {
        this.stylePersistence = stylePersistence;
        this.styleMapper = styleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StyleDto> getAllStyles() {
        List<Style> styles = this.stylePersistence.getAllStyles();
        return styleMapper.toDto(styles);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StyleDto> getAllStylesByCriteria(Pageable pageable, String search) {
        Page<Style> recentStylePage = this.stylePersistence.getAllStylesByCriteria(pageable, search);
        return recentStylePage.map(styleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StyleDto> getStyleById(Long styleId) {
        return this.stylePersistence.getStyleById(styleId).map(styleMapper::toDto);
    }

    @Override
    @Transactional
    public StyleDto createStyle(StyleDto styleDto) {
        Style style = this.stylePersistence.createStyle(styleMapper.toEntity(styleDto));
        return this.styleMapper.toDto(style);
    }

    @Override
    public Optional<StyleDto> updateStyle(Long styleId, StyleDto styleDto) {
        Optional<Style> existingStyleOptional = stylePersistence.getStyleById(styleId);
        if (existingStyleOptional.isPresent()) {
            Style existingStyle = existingStyleOptional.get();
            if (styleDto.getStyle() != null) {
                existingStyle.setStyle(styleDto.getStyle());
            }
            Style updateStyle = stylePersistence.createStyle(existingStyle);
            return Optional.of(styleMapper.toDto(updateStyle));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void deleteStyleById(Long styleId) {
        this.stylePersistence.deleteStyle(styleId);
    }
}
