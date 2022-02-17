package ru.mironov.marvelapi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mironov.marvelapi.domain.entity.Comic;
import ru.mironov.marvelapi.domain.exception.comic.ComicNotFoundException;
import ru.mironov.marvelapi.domain.mapper.ComicMapper;
import ru.mironov.marvelapi.repository.ComicRepository;
import ru.mironov.marvelapi.service.ComicService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author mironovAlexanderJR
 * @since 27.01.2022
 */
@Service
@Primary
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ComicServiceImpl implements ComicService {
    private final ComicRepository comicRepository;
    private final ComicMapper comicMapper;

    @Override
    public List<Comic> getAllComic() {
        return comicRepository.findAll();
    }

    @Override
    public Comic getComic(UUID comicId) {
        return comicRepository.findById(comicId)
                .orElseThrow(() -> new ComicNotFoundException(comicId));
    }

    @Override
    @Transactional
    public Comic createComic(Comic comicJson) {
        return comicRepository.save(comicJson);
    }

    @Override
    @Transactional
    public Comic updateComic(UUID comicId, Comic comicJson) {
        return Optional.of(comicId)
                .map(this::getComic)
                .map(current -> comicMapper.merge(current, comicJson))
                .map(comicRepository::save)
                .orElseThrow(() -> new ComicNotFoundException(comicId));
    }

    @Override
    @Transactional
    public void deleteComic(UUID comicId) {
        comicRepository.deleteById(comicId);
    }
}
