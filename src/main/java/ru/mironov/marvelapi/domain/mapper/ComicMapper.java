package ru.mironov.marvelapi.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.entity.Comic;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Mapper
public interface ComicMapper {
    @Mapping(target = "id", ignore = true)
    Comic fromCreateDto(ComicCreateDto source);

    Comic fromUpdateDto(ComicUpdateDto source);

    ComicDto toDto(Comic source);

    ComicInfoDto toInfoDto(Comic source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Comic merge(@MappingTarget Comic target, Comic source);
}
