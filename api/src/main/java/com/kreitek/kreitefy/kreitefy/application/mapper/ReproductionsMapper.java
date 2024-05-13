package com.kreitek.kreitefy.kreitefy.application.mapper;

import com.kreitek.kreitefy.kreitefy.application.dto.ReproductionsDto;
import com.kreitek.kreitefy.kreitefy.domain.entity.Reproductions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "apring", uses = { UserMapper.class , SongMapper.class })
public interface ReproductionsMapper extends EntityMapper<Reproductions, ReproductionsDto> {

    @Override
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "song.id", target = "songId")
    ReproductionsDto toEntity(Reproductions dto);

    @Override
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "songId", target = "song.id")
    Reproductions toDto(ReproductionsDto entity);
}
