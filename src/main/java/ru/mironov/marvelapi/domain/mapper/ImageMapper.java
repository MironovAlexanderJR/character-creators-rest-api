package ru.mironov.marvelapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Image;

/**
 * @author mironovAlexanderJR
 * @since 15.01.2022
 */
@Mapper
public interface ImageMapper {
    @Mapping(target = "imageDownloadUrl", ignore = true)
    ImageDto toDto(Image source);
}
