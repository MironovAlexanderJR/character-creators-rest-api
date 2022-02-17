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
    @Mapping(target = "comics", ignore = true)
    @Mapping(target = "creators", ignore = true)
    Character fromCreateDto(CharacterCreateDto source);

    @Mapping(target = "comics", ignore = true)
    @Mapping(target = "creators", ignore = true)
    Character fromUpdateDto(CharacterUpdateDto source);

    CharacterDto toDto(Character source);

    CharacterInfoDto toInfoDto(Character source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Character merge(@MappingTarget Character target, Character source);
}
