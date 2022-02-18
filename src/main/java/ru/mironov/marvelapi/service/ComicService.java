package ru.mironov.marvelapi.service;

import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Comic;

import java.util.List;
import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface ComicService {
    List<Comic> getAllComic();

    Comic getComic(UUID comicId);

    Comic createComic(Comic comicJson);

    Comic updateComic(UUID comicId, Comic comicJson);

    void deleteComic(UUID comicId);

    ImageDto uploadImage(UUID creatorsId, MultipartFile image);

    void deleteImage(UUID creatorId, UUID imageId);
}
