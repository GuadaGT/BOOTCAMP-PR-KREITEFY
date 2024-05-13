package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.ReproductionsDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserMapper.class , SongMapper.class })
public interface ReproductionsMapper extends EntityMapper<ReproductionsDto, Reproductions> {

    @Override
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "songId", target = "song.id")
    Reproductions toEntity(ReproductionsDto dto);

    @Override
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "song.id", target = "songId")
    ReproductionsDto toDto(Reproductions entity);

    default Reproductions fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reproductions reproduction = new Reproductions();
        reproduction.setId(id);
        return reproduction;
    }

}
