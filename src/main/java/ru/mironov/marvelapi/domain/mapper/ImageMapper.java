package ru.mironov.marvelapi.domain.mapper;

import org.mapstruct.Mapper;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Image;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
@Mapper
public interface ImageMapper {
    ImageDto toDto(Image source);
}
