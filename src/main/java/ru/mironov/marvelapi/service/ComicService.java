package ru.mironov.marvelapi.service;

import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Comic;

import java.util.List;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
public interface ComicService {
    List<Comic> getAllComic();

    Comic getComic(Long comicId);

    Comic createComic(Comic comicJson);

    Comic updateComic(Long comicId, Comic comicJson);

    void deleteComic(Long comicId);
}
