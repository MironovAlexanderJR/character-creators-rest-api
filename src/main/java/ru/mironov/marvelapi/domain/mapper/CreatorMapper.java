package ru.mironov.marvelapi.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.mironov.marvelapi.domain.dto.creator.CreatorCreateDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorInfoDto;
import ru.mironov.marvelapi.domain.dto.creator.CreatorUpdateDto;
import ru.mironov.marvelapi.domain.entity.Creator;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Mapper
public interface CreatorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "characters", ignore = true)
    @Mapping(target = "comics", ignore = true)
    @Mapping(target = "imageDownloadUrl", ignore = true)
    Creator fromCreateDto(CreatorCreateDto source);

    @Mapping(target = "characters", ignore = true)
    @Mapping(target = "comics", ignore = true)
    @Mapping(target = "imageDownloadUrl", ignore = true)
    Creator fromUpdateDto(CreatorUpdateDto source);

    CreatorDto toDto(Creator source);

    CreatorInfoDto toInfoDto(Creator source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Creator merge(@MappingTarget Creator target, Creator source);
}
