package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.StyleDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.Style;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SongMapper.class})
public interface StyleMapper extends EntityMapper<StyleDto, Style> {
    default Style fromId(Long id) {
        if (id == null) {
            return null;
        }
        Style style = new Style();
        style.setId(id);
        return style;
    }

    @Mapping(target ="songs", ignore = true)
    Style toEntity(StyleDto dto);
    StyleDto toDto(Style entity);
}
