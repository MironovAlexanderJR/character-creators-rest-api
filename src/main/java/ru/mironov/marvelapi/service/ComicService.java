package ru.mironov.marvelapi.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.mironov.marvelapi.domain.dto.comic.ComicCreateDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicInfoDto;
import ru.mironov.marvelapi.domain.dto.comic.ComicUpdateDto;
import ru.mironov.marvelapi.domain.dto.file.ImageDto;
import ru.mironov.marvelapi.domain.entity.Comic;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface ComicService {
    Page<ComicDto> getAllComics(Pageable pageable);

    ComicDto getAndInitializeComic(UUID comicId);

    Comic localMethodGetComicById(UUID comicId);

    ComicInfoDto getComicInfo(UUID comicId);

    ComicDto createComic(ComicCreateDto comicCreateDto);

    ComicDto updateComic(UUID comicId, ComicUpdateDto comicUpdateDto);

    void deleteComic(UUID comicId);

    ImageDto uploadImage(UUID creatorsId, MultipartFile image);

    void deleteImage(UUID creatorId, UUID imageId);
}
