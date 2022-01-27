package ru.mironov.marvelapi.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.mironov.marvelapi.domain.dto.character.CharacterCreateDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterInfoDto;
import ru.mironov.marvelapi.domain.dto.character.CharacterUpdateDto;
import ru.mironov.marvelapi.domain.entity.Character;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Mapper
public interface CharacterMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comic", ignore = true)
    Character fromCreateDto(CharacterCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comic", ignore = true)
    Character fromUpdateDto(CharacterUpdateDto source);

    @Mapping(target = "comicDto", ignore = true)
    @Mapping(target = "creatorDto", ignore = true)
    CharacterDto toDto(Character source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comicId", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    CharacterInfoDto toInfoDto(Character source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Character merge(@MappingTarget Character target, Character source);
}
