package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.mironov.marvelapi.domain.entity.Character;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.exception.comic.ComicNotFoundException;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.repository.ComicRepository;
import ru.mironov.marvelapi.service.ComicService;

import java.util.List;
import java.util.Optional;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
public class ComicServiceImpl implements ComicService {
    private final ComicRepository comicRepository;
    private final ComicMapper comicMapper;

    @Override
    public List<Comic> getAllComic() {
        return comicRepository.findAll();
    }

    @Override
    public Comic getComic(Long comicId) {
        return comicRepository.findById(comicId)
                .orElseThrow(() -> new ComicNotFoundException(comicId));
    }

    @Override
    public Comic createComic(Comic comicJson) {
        return comicRepository.save(comicJson);
    }

    @Override
    public Comic updateComic(Long comicId, Comic comicJson) {
        return Optional.of(comicId)
                .map(this::getComic)
                .map(current -> comicMapper.merge(current, comicJson))
                .map(comicRepository::save)
                .orElseThrow(() -> new ComicNotFoundException(comicId));
    }

    @Override
    public void deleteComic(Long comicId) {
        final Comic comic = comicRepository.findById(comicId).orElseThrow();
        comicRepository.delete(comic);
    }
}
